package com.test.day_171130.demo3;

public class TestByte {

    /**
     * 把4个字节包装（packing）成一个int
     * @param b
     * @return
     */
    public static int packBigEndian(byte[] b){
        if (null == b)
            throw new NullPointerException();
        if(b.length < 4)
            throw new RuntimeException();
        return (b[0] & 0xFF) << 24
                | (b[1] & 0xFF) << 16
                | (b[2] & 0xFF) <<  8
                | (b[3] & 0xFF) <<  0;
    }

    public static byte[] unpackBigEndian(int x){
        return new byte[]{
                (byte) (x >>> 24),
                (byte) (x >>> 16),
                (byte) (x >>> 8),
                (byte) (x >>> 0)
        };
    }

    public static void main(String[] args) {
        //byte[] b = {0,0,0,1};
        //byte[] b = {0,0,1,0};
        /*byte[] b = {0,1,0,0};*/
        byte[] b = {1,0,0,0};
        System.out.println(TestByte.packBigEndian(b));

        byte[] bb = TestByte.unpackBigEndian(1);
        for (int i=0;i<bb.length;i++){
            System.out.print(Integer.toBinaryString(bb[i]));
        }

    }
}