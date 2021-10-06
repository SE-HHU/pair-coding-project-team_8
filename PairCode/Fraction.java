
package PairCode;

/**
 * 分数
 *
 * 用类对分数进行模拟，
 * 本类中的a为分数的分子，b为分数的分母
 *
 * @author 86189
 * @date 2021/10/06
 */
public class Fraction {
    int a,b;

    //a表示分数的分子，b表示分数的分母

    public Fraction() {
        this.a = 0;
        this.b = 0;
    }
    //初始化

    public Fraction(int a,int b)
    {
        this.a = a;
        this.b = b;
    }
    //初始化

    public void create(int a,int b)
    {
        this.a= a; this.b = b;
    }


}
