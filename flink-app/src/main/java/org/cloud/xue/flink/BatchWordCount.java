package org.cloud.xue.flink;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.FlatMapOperator;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

/**
 * @ClassName WordCount
 * @Description: First Flink Application 批处理
 * @Author: Doggie
 * @Date: 2023年08月23日 10:12:28
 * @Version 1.0
 **/
public class BatchWordCount {
    public static void main(String[] args) throws Exception{
        // 创建执行环境
        ExecutionEnvironment environment = ExecutionEnvironment.getExecutionEnvironment();

        // 读取文件数据（获得的数据是一行一行的）
        String filePath = "/Users/xuexiao/Work/QDBank/UEMP/flink-app/src/main/resources/data/hello-flink.txt";
//        String filePath = "/home/uemp/flink-1.17.0/data/hello-flink.txt";
        DataSet<String> inputDataSet = environment.readTextFile(filePath);

        // 扁平化：将一行一行的数据分解成一个一个的单词
        // line => (word1, 1), (word2, 1), (word3, 1), (word1, 1)
        // 将多个无关数据组合在一起的结构，叫做元组（元素的组合Tuple）
        FlatMapOperator<String, Tuple2<String, Integer>> flatMapOperator = inputDataSet.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
            public void flatMap(String s, Collector<Tuple2<String, Integer>> out) throws Exception {
                String[] words = s.split(" ");
                for (String word : words) {
                    out.collect(new Tuple2<String, Integer>(word, 1));
                }
            }
        });

        // 将转换结构后的单词进行分组，相同的单词放在一组
        // 按照Tuple2中的第一个元素分组：(word1, ((word1,1),(word1,1)))
        // 按照Tuple2中的第二个元素进行统计聚合
        DataSet<Tuple2<String, Integer>> resultSet = flatMapOperator.groupBy(0).sum(1);

        // 打印结果
        resultSet.print();
    }
}
