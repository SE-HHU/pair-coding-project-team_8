package PairCode;

import java.util.Vector;
/**
 * 判定模块
 *
 * Right_or_Wrong
 * 对读入的式子经由格式处理后，进行计算
 * 后与读入的答案经由格式处理后，进行对比
 *
 * 得到正确|错误的答案个数，以及其序号
 *
 * judge_repeat
 * 对式子中通过对比提取，具有相同答案的式子
 * 再经由fin_judge函数，进行全面比对，敲定真正重复的式子
 *
 * @author 86189
 * @date 2021/09/24
 */
public class Judgement {
    public boolean compare(Fraction a, Fraction b) {
        return a.a == b.a && a.b == b.b;
    }
    // 判定读入的式子答案正确与否

    /**
     * 对或错
     *
     * @param store 商店
     */
    public void Right_or_Wrong( Storage store){
        for(int i= 0 ;i < store.ansForTest.size();i++) {
            if (compare(store.ansForTest.get(i), store.ansForWrite.get(i))) {
                store.Correct_array.add(i+1);
                store.correct_num++;
            }
            else {
                store.Wrong_array.add(i+1);
                store.wrong_num++;
            }
        }
    }

    //详细比较是否重复，敲定最终重复的式子序号
    public boolean finalJudge(StringBuilder one, StringBuilder two)
    {
        String first = one.toString();
        String second = two.toString();

        boolean flag  = true;
        for(int i = 0 ; i< first.length() -1 ;i ++)
        {
            flag= false;
            if(first.charAt(i)== ')' || first.charAt(i) == '(') {
                continue;
            }
            for(int j = 0 ; j < second.length() -1 ;j++) {
                if(first.charAt(i) == second.charAt(j))
                {flag = true; break;}
            }

            if (!flag) {
                break;
            }
        }
         return flag;
    }

    //通过答案相同，进行初步判重
    public void judgeRepeat(Storage store)
    {
        int index= 0;
        boolean[] st = new boolean[store.ansForWrite.size()];

        for(int i = 0 ;i< store.ansForWrite.size()-1 ; i++)
        {
            int flag = 0;
            int j ;
            for(j = i + 1; j<store.ansForWrite.size();j++)
            {
                if(compare(store.ansForWrite.get(i) , store.ansForWrite.get(j))&& !st[j]) {
                    if(flag == 0) {
                        Vector<Integer> init = new Vector<>();
                        store.potential_repeat.add(init);
                        store.potential_repeat.get(index).add(i + 1);
                        flag = 1;

                    }
                    st[j] = true;
                    store.potential_repeat.get(index).add(j+1);
                }
            }

            if(flag ==1 ) {
                index++;
            }
        }
        index = 0;

        //找出潜在的可能重复的式子（答案相等）

        //potential 类似一个二维数组

        for(int i = 0; i< store.potential_repeat.size(); i++)
        {
                int j = 0;
                int index_one ,index_two;
                int flag = 0;
                index_one = store.potential_repeat.get(i).get(j);

                for(int k = j+1;k<store.potential_repeat.get(i).size(); k++)
                {
                    index_two = store.potential_repeat.get(i).get(k);
                    if(finalJudge(store.formulationsForCalculation.get(index_one-1),
                        store.formulationsForCalculation.get(index_two-1))) {
                        if(flag == 0) {
                            Vector<Integer> init = new Vector<>();
                            store.real_repeat.add(init);
                            flag =1;
                            store.real_repeat.get(index).add(index_one);
                        }
                        store.real_repeat.get(index).add(index_two);
                    }
                }
        }
    }
}
