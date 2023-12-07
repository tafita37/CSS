package css;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class CssFile {
    File file;

    public CssFile(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void writeLineInFile(String contenu)
    throws Exception {
        FileWriter fileWriter=new FileWriter(file, true);
        BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
        bufferedWriter.write(contenu);
        bufferedWriter.newLine();
        bufferedWriter.close();
        fileWriter.close();
    }

    public void writeLineInFileAndChange(String contenu)
    throws Exception {
        FileWriter fileWriter=new FileWriter(file, false);
        BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
        bufferedWriter.write(contenu);
        bufferedWriter.newLine();
        bufferedWriter.close();
        fileWriter.close();
    }

    public void createVariable(String name, String value)
    throws Exception {
        ArrayList<String> allContent=this.readAllContent();
        ArrayList<String> result=new ArrayList<String>();
        int k=0;
        for(int i=0; i<allContent.size(); i++) {
            if(allContent.get(i).compareTo(":root {")==0) {
                k++;
            } else if(k==1) {
                result.add("\t--"+name+": "+value+";");
                k++;
            } 
            result.add("--"+allContent.get(i));
        }
        this.writeLineInFileAndChange("");
        for(int i=0; i<result.size(); i++) {
            this.writeLineInFile(result.get(i));
        }
    }

    public HashMap<String, String> readContent() {
        HashMap<String, String> result=new HashMap<String, String>();
        try {
            BufferedReader lecteur = new BufferedReader(new FileReader(this.getFile()));
            String ligne;
            int i=0;
            while ((ligne = lecteur.readLine()) != null) {
                if(ligne.compareTo(":root {")==0) {
                    i++;
                }
                else if(i>0&&ligne.compareTo("}")!=0) {
                    result.put(ligne.split(":")[0].trim().substring(2), ligne.split(":")[1].trim());
                }
                else if(ligne.compareTo("}")==0) {
                    break;
                }
            }
            lecteur.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<String> readAllContent() {
        ArrayList<String> result=new ArrayList<String>();
        try {
            BufferedReader lecteur = new BufferedReader(new FileReader(this.getFile()));
            String ligne;
            int i=0;
            while ((ligne = lecteur.readLine()) != null) {
                result.add(ligne);
            }
            lecteur.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void changeCssValueOfVariable(String[] variable, String[] newValue)
    throws Exception {
        ArrayList<String> allContent=this.readAllContent();
        for(int i=0; i<variable.length; i++) {
            String oldValue=this.readContent().get(variable[i]);
            for(int j=0; j<allContent.size(); j++) {
                if(allContent.get(j).trim().compareTo(("--"+variable[i]+": "+oldValue).trim())==0) {
                    allContent.set(j, "--"+variable[i]+": "+newValue[i]);
                    break;
                }
            }
        }
        this.writeLineInFileAndChange("");
        for(int i=0; i<allContent.size(); i++) {
            this.writeLineInFile(allContent.get(i));
        }
    }
}
