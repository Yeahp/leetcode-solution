package marix.calculation;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

public class ND4JDemo {

    private INDArray indArray;

    public INDArray addArray(INDArray indArray) {
        return this.indArray.add(indArray);
    }

    public INDArray addArrayi(INDArray indArray) {
        return this.indArray.addi(indArray);
    }

    public INDArray addNum(int num) {
        return this.indArray.add(num);
    }

    public INDArray addNumi(int num) {
        return this.indArray.addi(num);
    }

    public INDArray subArray(INDArray indArray) {
        return this.indArray.sub(indArray);
    }

    public INDArray subArrayi(INDArray indArray) {
        return this.indArray.subi(indArray);
    }

    public INDArray subNum(int num) {
        return this.indArray.sub(num);
    }

    public INDArray subNumi(int num) {
        return this.indArray.subi(num);
    }

    public INDArray mulArray(INDArray indArray) {
        return this.indArray.mul(indArray);
    }

    public INDArray mulArrayi(INDArray indArray) {
        return this.indArray.muli(indArray);
    }

    public INDArray mulNum(int num) {
        return this.indArray.mul(num);
    }

    public INDArray mulNumi(int num) {
        return this.indArray.muli(num);
    }

    public INDArray divArray(INDArray indArray) {
        return this.indArray.div(indArray);
    }

    public INDArray divArrayi(INDArray indArray) {
        return this.indArray.divi(indArray);
    }

    public INDArray divNum(int num) {
        return this.indArray.div(num);
    }

    public INDArray divNumi(int num) {
        return this.indArray.divi(num);
    }

    public INDArray transpose() {
        return this.indArray.transpose();
    }

    public INDArray transposei(INDArray indArray) {
        return this.indArray.transposei();
    }

    public INDArray zeros() {
        return Nd4j.zeros();
    }

    public INDArray ones() {
        return Nd4j.ones();
    }

    public INDArray hstack(INDArray indArray1, INDArray indArray2) {
        return Nd4j.hstack(indArray1, indArray2);
    }

    public INDArray vstack(INDArray indArray1, INDArray indArray2) {
        return Nd4j.vstack(indArray1, indArray2);
    }

    public INDArray randRowCol(int row, int col) {
        return Nd4j.rand(row, col);
    }

    public INDArray randDims(int[] shape) {
        return Nd4j.rand(shape);
    }


    //    INDArray putScalar(int[] i, double value)：对应位置设置标量
    //
    //    INDArray putScalar(int row, int col, double value)：对应行列处设置标量
    //
    //    INDArray put(INDArrayIndex[] indices, INDArray element):对应维度处设置INDArray
    //
    //    8、其他操作
    //
    //    INDArray reshape(int... newShape)：重新定义张量形状

    public static void main(String[] args) {
        ND4JDemo nd4JDemo = new ND4JDemo();
        nd4JDemo.indArray = Nd4j.create(new float[]{1.0f, 2.0f, 3.0f, 4.0f});
        System.out.println(String.format("%d-%d", nd4JDemo.indArray.shape()[0], nd4JDemo.indArray.shape()[1]));
    }
}
