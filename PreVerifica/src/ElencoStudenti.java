import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class ElencoStudenti extends ArrayList<Studente>{
	
	public ElencoStudenti() {}
	public Studente s;
	
	public void salvasucsv(Finestra f) {
		JFileChooser chooser=new JFileChooser();
		chooser.setFileFilter(new CsvFileFilter());
		int n=chooser.showSaveDialog(f);
		if(JFileChooser.APPROVE_OPTION==n) {
			File file=chooser.getSelectedFile();
			FileWriter fw;
			try {
				fw = new FileWriter(file);
				for(int i=0;i<this.size();i++) {
					fw.write(this.get(i).nome+", "+this.get(i).cognome+", "+this.get(i).classe+", "+this.get(i).path+",\n");
				}
				fw.flush();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		JOptionPane.showMessageDialog(f, "Salvataggio avvenuto con successo");
	}

	
	public void importadacsv(Finestra f) {
		JFileChooser chooser=new JFileChooser();
		chooser.setFileFilter(new CsvFileFilter());
		int n=chooser.showOpenDialog(f);
		if(JFileChooser.APPROVE_OPTION==n) {
			File file=chooser.getSelectedFile();
			BufferedReader br;
			try {
				br = new BufferedReader(new FileReader(file));
				String line="";
				while((line=br.readLine())!=null) {
					String[] s=line.split(", ");
					this.add(new Studente(s[0],s[1],s[2],s[3]));
				}
				JOptionPane.showMessageDialog(f, "Serializzazione effettuata");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(f,"Importazione avvenuta con successo");
		}
	}
	
	public void salvaserial(Finestra finestra) {
		FileOutputStream f=null;
		ObjectOutputStream o=null;
		JFileChooser chooser=new JFileChooser();
		chooser.setFileFilter(new CsvFileFilter());
		int n=chooser.showSaveDialog(finestra);
		if(n==JFileChooser.APPROVE_OPTION) {
			File file=chooser.getSelectedFile();
			try {
				f=new FileOutputStream(file);
				o=new ObjectOutputStream(f);
				o.writeObject(this);
				o.flush();
				o.close();
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void deserializza(Finestra finestra) {
		FileInputStream f=null;
		ObjectInputStream o=null;
		JFileChooser chooser=new JFileChooser();
		chooser.setFileFilter(new CsvFileFilter());
		int n=chooser.showSaveDialog(finestra);
		if(n==JFileChooser.APPROVE_OPTION) {
			File file=chooser.getSelectedFile();
			try {
				f=new FileInputStream(file);
				o=new ObjectInputStream(f);
				ElencoStudenti es=(ElencoStudenti)o.readObject();
				o.close();
				for(int i=0;i<es.size();i++) {
					this.add(es.get(i));
				}
				JOptionPane.showMessageDialog(finestra, "Deserializzazione effettuata");
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}


