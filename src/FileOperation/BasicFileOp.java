package FileOperation;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.net.URI;

/**
 * Created by yml on 16/5/23.
 */
public class BasicFileOp {
    private final String url = "hdfs://localhost:9000";
    private final Configuration conf = new Configuration();

    public static void main(String[] args) throws Exception {
        BasicFileOp basicFileOp = new BasicFileOp();
        basicFileOp.IsExest();
    }

    public void IsExest() throws Exception {
        FileSystem fs = FileSystem.get(URI.create(url), conf);
        Path path = new Path("/home/hadoop/input/1.txt");
        boolean ok = fs.exists(path);
        System.out.println(ok ? "文件存在" : "文件不存在");
        fs.close();
    }

    public void RunApplication() throws Exception {
        FileSystem fileSystem = FileSystem.get(URI.create(url), conf);
        Path path = new Path("/");
        fileSystem.getChildFileSystems();
        fileSystem.close();
    }
}
