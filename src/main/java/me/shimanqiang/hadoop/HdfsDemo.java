package me.shimanqiang.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

import java.net.URL;

/**
 * @author shimanqiang
 * @since 2019/10/31 19:48
 */
public class HdfsDemo {

    public static void main(String[] args) throws Exception {
        System.setProperty("HADOOP_USER_NAME", "root");
//        System#setProperty("HADOOP_HOME", "/Users/shimanqiang/bigData/hadoop-3.2.1");
//        System#setProperty("hadoop.home.dir", "/Users/shimanqiang/bigData/hadoop-3.2.2");
        System.setProperty("hadoop.home.dir", "/");

        //设定开启HDFS协议
        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());

        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://hadoop001:8020");
        conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
        conf.set("dfs.client.block.write.replace-datanode-on-failure.policy", "NEVER");
        FileSystem fs = FileSystem.get(conf);

        System.out.println(fs);


        //write
        Path path = new Path("/import/tmp/Wtest.txt");
        FSDataOutputStream fout = fs.create(path);
        byte[] bWrite = "hello hadoop distribute file system \n".getBytes();
        //写入字节数组
        fout.write(bWrite);
        //flush提供了一种将缓冲区的数据强制刷新到文件系统的方法
        fout.flush();
        //关闭写出流
        fout.close();

        fout = fs.append(path);
        fout.write("append: the append method of java API \n".getBytes());
        //关闭写出流
        fout.close();

        //read
        FSDataInputStream fin = fs.open(path);
        byte[] buff = new byte[128];
        int len = 0;

        while ((len = fin.read(buff, 0, 128)) != -1) {
            System.out.print(new String(buff, 0, len));
        }

        //创建目录
        if (fs.mkdirs(new Path("/import/test"))) {
            System.out.println("mkdir /import/test success ");
        }

//        //列出目录
//        FileStatus[] paths = fs.listStatus(new Path("/import"));
//        for (int i = 0; i < paths.length; ++i) {
//            System.out.println(paths[i].toString());
//            System.out.println(paths[i].getLen());
//            System.out.println(paths[i].isDirectory());
//            System.out.println(paths[i].getPath().getParent());
//            System.out.println(paths[i].getPath());
//            System.out.println(paths[i].getPath().getName());
//        }
//
//        //删除
//        if (fs.delete(new Path("/import"), true)) {
//            System.out.println("delete directory /import ");
//        }
//
//        fin.close();
//        fs.close();
    }
}
