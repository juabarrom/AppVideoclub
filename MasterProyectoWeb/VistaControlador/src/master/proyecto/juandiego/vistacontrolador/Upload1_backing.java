package master.proyecto.juandiego.vistacontrolador;

import java.io.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;

@ManagedBean(name="upload1Backing1")
@RequestScoped
public class Upload1_backing{

    private static final String DESTINO = "/imagen";
    private String path;
    private UploadedFile file;
    
    public Upload1_backing()
    {
    }

    public void setFile(UploadedFile file)
    {
        System.out.println("Archivoset "+file.getFileName().toString());
        this.file = file;
        uploadImage(file);
    }

    public UploadedFile getFile()
    {
        return file;
    }

    public void setPath(String path)
    {
        this.path = path;
    }

    public String getPath()
    {
        return path;
    }

    private void uploadImage(UploadedFile uploadedFile)
    {
        int linea = 0;
        byte bytes[] = new byte[1024];
        InputStream in = null;
        OutputStream out = null;
        /*
        //PATH DONDE DEBERIA IR UNA VEZ SUBIDA INDEFINIDAMENTE AL SERVIDOR
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/imagen");
        setPath(path);
        */
        
        try
        {
            
            in = uploadedFile.getInputstream();
            out = new FileOutputStream(new File("/Users/juandiegobarreraroman/Desktop/MASTER_WEB/MasterProyectoWeb/VistaControlador/public_html/resources/images" + File.separator + file.getFileName().toString()));
            while((linea = in.read(bytes)) != -1) 
                out.write(bytes, 0, linea);
            
            in.close();
            out.flush();
            out.close();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "El archivo ", (new StringBuilder()).append(uploadedFile.getFileName()).append(" fue cargado de manera exitosa!!").toString());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        catch(IOException e)
        {
            e.printStackTrace();
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Error al subir el archivo");
            FacesContext.getCurrentInstance().addMessage("", mensaje);
        }
    }

    
}
