package ManagedMe.beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

@ManagedBean
public class FileDownloadView {
     
    private StreamedContent file;
     
    public FileDownloadView() {   
        System.out.println("Entraaa");
        InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/Applications/NetBeans/glassfish-4.1/glassfish/domains/domain1/generated/jsp/ManageMe/subir3.xhtml");
        file = new DefaultStreamedContent(stream, "/Users/xhtml", "downloaded_optimus.jpg");
     
    }
 
    public StreamedContent getFile() {
        return file;
    }
    
    
    public void downloadFile(File file) {   
    FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
    response.setHeader("Content-Disposition", "attachment;filename=subir3.xhtml");
    response.setContentLength((int) file.length());
    FileInputStream input= null;
    try {
        int i= 0;
        input = new FileInputStream(file);  
        byte[] buffer = new byte[1024];
        while ((i = input.read(buffer)) != -1) {  
            response.getOutputStream().write(buffer);  
            response.getOutputStream().flush();  
        }               
        facesContext.responseComplete();
        facesContext.renderResponse();
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
            if(input != null) {
                input.close();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
    
    
}
