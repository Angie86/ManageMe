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
import ManageMe.entity.Projects;
import ManageMe.entity.Users;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author inftel08
 */
@ManagedBean
@SessionScoped
public class HistoricBean {

    @ManagedProperty(value = "#{userBean}")
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

    public String upload() throws Exception {
	System.out.println("Estoy en fileUpload " + file1.getInputStream().toString());
	file1.write(getFilename(file1));

	Document doc = new Document();
	Historic hist = new Historic();
	Date date = new Date();
	doc.setNameDocument(description);
	doc.setTypeDocument(getFilename(file1).substring(getFilename(file1).lastIndexOf(".") + 1));
	doc.setDateDocument(date);
	doc.setPathDocument("/Users/inftel08/Applications/NetBeans/glassfish-4.1/glassfish/domains/domain1/generated/jsp/ManageMe/ManageMe-war_war/" + getFilename(file1));
	
        documentFacade.create(doc);
	hist.setIdDocument(doc);
	hist.setIdUser(userBean.user);
	hist.setIdProject(userBean.project);
	historicFacade.create(hist);
	listaHistorico = historicFacade.findHistoricByProjectId(userBean.project.getIdProject());
	return ("historicPage");

    }

    public String downloadFile(Historic historicRow) throws IOException {
	
	File newFile = new File(historicRow.getIdDocument().getPathDocument());
        System.out.println(historicRow.getIdDocument().getPathDocument());
	File targetFile = new File("/Users/inftel08/Desktop/PruebaDeDownload.txt");
	Path sourcePath = newFile.toPath();
	Path targetPath = targetFile.toPath();
	Files.move(sourcePath, targetPath, REPLACE_EXISTING);
	return "historicPage";
	
    }
//    public void downLoadfffffff() {
//	System.out.println("entraaaaaaa");
//	File newFile = new File(historicRow.getIdDocument().getPathDocument());
//	File targetFile = new File("/Users/noussairelharrak/Downloads/PruebaDeDownload.txt");
//	Path sourcePath = newFile.toPath();
//	Path targetPath = targetFile.toPath();
//	Files.move(sourcePath, targetPath, REPLACE_EXISTING);

    /*InputStream input = newFile.;
	try {
	    OutputStream output = new FileOutputStream("/Users/noussairelharrak/Downloads");
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
	}*/
//    }
    public String doShowHistoric(Projects project) {
	userBean.project = project;
	listaHistorico = historicFacade.findHistoricByProjectId(userBean.project.getIdProject());
	description = "";
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
	return document.getPathDocument().substring(document.getPathDocument().lastIndexOf("/") + 1);
    }

    public String getDateFileHistoricRow(Document document) {
	SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm dd-MM-yyyy");
	String strDate = sdfDate.format(document.getDateDocument());
	return strDate;
    }

}
