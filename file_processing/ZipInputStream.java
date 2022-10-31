import java.io.*;
import java.util.zip.*;
  
public class Program {
  
    public static void main(String[] args) {
         
        try(ZipInputStream zin = new ZipInputStream(new FileInputStream("C:\\SomeDir\\output.zip")))
        {
            ZipEntry entry;
            String name;
            long size;
            while((entry=zin.getNextEntry())!=null){
                  
                name = entry.getName(); // получим название файла
                size=entry.getSize();  // получим его размер в байтах
                System.out.printf("File name: %s \t File size: %d \n", name, size);
                 
                // распаковка
                FileOutputStream fout = new FileOutputStream("C:\\somedir\\new" + name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        }
        catch(Exception ex){
              
            System.out.println(ex.getMessage());
        } 
    } 
}
