package FileOperation;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

/**
 * Created by yml on 16/5/23.
 */
public class BasicFileOp {
    private final String url = "hdfs://localhost:9000";
    private final Configuration conf = new Configuration();

    public static void main(String[] args) throws Exception {
        BasicFileOp basicFileOp = new BasicFileOp();
        String str = "/home/hadoop/input/2.txt";
        String st = "Hello world!!!";
        String LocaPath = "/Users/yml/Documents";
        basicFileOp.IsExist(str);
        /*basicFileOp.CreateFile(str,st.getBytes());
        basicFileOp.DownloadFile(str,LocaPath);*/
        basicFileOp.UploadFile(LocaPath + "/2.txt", "/home/hadoop/input/3.txt");

    }

    public void IsExist(String fileName) throws Exception { //判断文件是否存在
        FileSystem fileSystem = FileSystem.get(URI.create(url), conf);
        Path path = new Path(fileName);
        boolean ok = fileSystem.exists(path);
        System.out.println(ok ? "文件存在" : "文件不存在");
        fileSystem.close();
    }

    public void CreateFile(String dst, byte[] contents) throws IOException {//创建文件,并向文件中写入数据
        FileSystem fileSystem = FileSystem.get(URI.create(url), conf);
        Path dstPath = new Path(dst);
        FSDataOutputStream outputStream = fileSystem.create(dstPath);
        outputStream.write(contents);
        outputStream.close();
        fileSystem.close();
        System.out.println("文件创建成功,并将数据写入文件");
    }

    public void Mkdir(String src) throws IOException {//创建目录
        FileSystem fileSystem = FileSystem.get(URI.create(url), conf);
        Path srcPath = new Path(src);
        boolean isok = fileSystem.mkdirs(srcPath);
        if (isok) {
            System.out.println("Create dir ok!");
        } else
            System.out.println("Create dir failure!");
        fileSystem.close();
    }

    public void ReadFile(String filePath) throws IOException {//读取文件的内容
        FileSystem fileSystem = FileSystem.get(URI.create(url), conf);
        Path srcPath = new Path(filePath);
        InputStream inputStream = null;
        try {
            inputStream = fileSystem.open(srcPath);
            IOUtils.copyBytes(inputStream, System.out, 4096, false);
        } finally {
            IOUtils.closeStream(inputStream);
        }
        fileSystem.close();
    }

    public void Delete(String filePath) throws IOException {//删除文件
        FileSystem fileSystem = FileSystem.get(URI.create(url), conf);
        Path path = new Path(filePath);
        boolean isok = fileSystem.deleteOnExit(path);
        if (isok)
            System.out.println("delete ok!");
        else
            System.out.println("delete failure!");
        fileSystem.close();
    }

    public void Rename(String OldNmae, String NewName) throws IOException {//重命名文件
        FileSystem fileSystem = FileSystem.get(URI.create(url), conf);
        Path OldPath = new Path(OldNmae);
        Path NewPath = new Path(NewName);
        boolean isok = fileSystem.rename(OldPath, NewPath);
        if (isok)
            System.out.println("Rename ok!");
        else
            System.out.println("Rename failure!");
        fileSystem.close();
    }

    public void UploadFile(String src, String dst) throws IOException {//上传本地文件
        FileSystem fileSystem = FileSystem.get(URI.create(url), conf);
        Path srcPath = new Path(src);//原路径
        Path dstPath = new Path(dst);//目标路径
        fileSystem.copyFromLocalFile(false, srcPath, dstPath);
        fileSystem.close();
    }

    public void DownloadFile(String src, String dst) throws IOException {//下载文件到本地
        FileSystem fileSystem = FileSystem.get(URI.create(url), conf);
        Path srcPath = new Path(src);//原路径
        Path dstPath = new Path(dst);//目标路径
        fileSystem.copyToLocalFile(false, srcPath, dstPath);
        fileSystem.close();
    }
    public void RunApplication() throws Exception {
        FileSystem fileSystem = FileSystem.get(URI.create(url), conf);
        Path path = new Path("/");
        fileSystem.getChildFileSystems();
        fileSystem.close();
    }
}
