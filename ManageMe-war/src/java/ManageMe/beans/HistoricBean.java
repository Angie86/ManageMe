/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageMe.beans;

import ManageMe.ejb.DataUsersFacade;
import ManageMe.ejb.DocumentFacade;
import ManageMe.ejb.HistoricFacade;
import ManageMe.ejb.ProjectsFacade;
import ManageMe.entity.DataUsers;
import ManageMe.entity.Document;
import ManageMe.entity.Historic;
import ManageMe.entity.Users;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author inftel08
 */
@ManagedBean
@RequestScoped
public class HistoricBean {
    
    @ManagedProperty(value ="#{userBean}")
    protected UserBean userBean;

    @EJB
    private HistoricFacade historicFacade;

    @EJB
    private DataUsersFacade dataUsersFacade;
    
    @EJB
    private DocumentFacade documentFacade;
    
    @EJB
    private ProjectsFacade projectsFacade;

    

    /**
     * Creates a new instance of historicBean
     */
    protected List<Historic> listaHistorico;
    protected DataUsers dataUser;
    protected String description;

    private UploadedFile file;
    private Part file1;

    @PostConstruct
    public void HistoricBean() {
	listaHistorico = historicFacade.findHistoricByProjectId(21L);
	description="";
    }

    public List<Historic> getListaHistorico() {
	return listaHistorico;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }
    
    public void setListaHistorico(List<Historic> listaHistorico) {
	this.listaHistorico = listaHistorico;
    }

    public DataUsers getDataUser() {
	return dataUser;
    }

    public void setDataUser(DataUsers dataUser) {
	this.dataUser = dataUser;
    }

    public UserBean getUserBean() {
	return userBean;
    }

    public void setUserBean(UserBean userBean) {
	this.userBean = userBean;
    }

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
	
	Document doc = new Document();
	Historic hist = new Historic();
	Date date = new Date();
	doc.setNameDocument(description);
	doc.setTypeDocument(getFilename(file1).substring(getFilename(file1).lastIndexOf(".")+1));
	doc.setDateDocument(date);
	doc.setPathDocument("/Users/noussairelharrak/GlassFish_Server/glassfish/domains/domain1/generated/jsp/ManageMe/ManageMe-war_war/"+getFilename(file1));
	documentFacade.create(doc);
	hist.setIdDocument(doc);
	hist.setIdUser(userBean.user);
	hist.setIdProject(projectsFacade.findProjectById(21L));
	historicFacade.create(hist);
	listaHistorico = historicFacade.findHistoricByProjectId(21L);
	doShowHistoric();
	
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

    public String doShowHistoric() {
	return ("historicPage");
    }

    public String getPhotoUserHistoricRow(Users user) {
	dataUser = dataUsersFacade.findByIdUser(user);
	return dataUser.getPhotoUser();
    }
    
    public String getNameUserHistoricRow(Users user) {
	dataUser = dataUsersFacade.findByIdUser(user);
	return dataUser.getNameUser();
    }
    
    public String getNameFileHistoricRow(Document document) {
	return document.getPathDocument().substring(document.getPathDocument().lastIndexOf("/")+1);
    }
    
    public String getDateFileHistoricRow(Document document) {
	SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm dd-MM-yyyy");
        String strDate = sdfDate.format(document.getDateDocument());
	return strDate;
    }

}
