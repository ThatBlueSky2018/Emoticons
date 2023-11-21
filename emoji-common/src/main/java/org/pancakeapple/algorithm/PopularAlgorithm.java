package org.pancakeapple.algorithm;

public class PopularAlgorithm {
    private static final double weight_hits = 0.2;
    private static final double weight_comments = 0.2;
    private static final double weight_downloads = 0.2;
    private static final double weight_favorite = 0.2;
    private static final double weight_time = 0.2;

    /**
     * 归一化
     * @param min 最小值
     * @param max 最大值
     * @param value 实际值
     * @return 归一化结果，0~1之间
     */
    public static double normalization(int min,int max,int value) {
        if(max == min) {
            return 0;
        }
        return (double)(value-min)/(max-min);
    }

    /**
     * 计算时间衰减值
     * @param value 距今天数
     * @return 0~1之间的数字
     */
    public static double timeDecay(Long value) {
        return 1/Math.exp((double) value);
    }

    /**
     * 计算热门指数
     * @param hits 归一化之后的点击量
     * @param comments 归一化之后的评论量
     * @param downloads 归一化之后的下载量
     * @param favorite 归一化之后的收藏量
     * @param time 归一化之后的时间衰减值
     * @return 热门指数
     */
    public static Double calculateHotIndex(double hits,double comments,double downloads,double favorite,double time) {
        return weight_hits * hits + weight_comments * comments + weight_downloads * downloads
                + weight_favorite * favorite + weight_time * time;
    }
}
