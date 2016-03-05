package ManageMe.beans;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

@ManagedBean
public class FileUploadView {

    private UploadedFile file;
    private Part file1;

    public UploadedFile getFile() {
        return file;
    }

    public Part getFile1() {
        return file1;
    }

    public void setFile1(Part file1) {
        this.file1 = file1;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

//    public void upload() throws IOException {
////        if(file != null) {
////            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
////            FacesContext.getCurrentInstance().addMessage(null, message);
////        }
//        file1.write(getFilename(file1));
//    }
    private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }

    public void upload() throws Exception {
        System.out.println("Estoy en fileUpload " + file1.getInputStream().toString());
        file1.write(getFilename(file1));
      
///Users/noussairelharrak/GlassFish_Server/glassfish/domains/domain1/generated/jsp/ManageMe/ManageMe-war_war        
    }
    
    
    public void downLoad() throws IOException {
        byte[] buffer = new byte[8 * 1024];

        InputStream input = file1.getInputStream();
        try {
            OutputStream output = new FileOutputStream("/Users/inftel08/Desktop/desc.txt");
            try {
                int bytesRead;
                while ((bytesRead = input.read(buffer)) != -1) {
                    output.write(buffer, 0, bytesRead);
                }
            } finally {
                output.close();
            }
        } finally {
            input.close();
        }

    }
    
}
