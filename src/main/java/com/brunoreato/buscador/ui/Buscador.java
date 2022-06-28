package com.brunoreato.buscador.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.brunoreato.buscador.FinderConfiguration;
import com.brunoreato.buscador.index.FileIndex;
import com.brunoreato.buscador.index.MemoryIndex;
import com.brunoreato.buscador.model.Folder;
import com.brunoreato.buscador.model.WordOccurrenceList;
import com.brunoreato.buscador.model.WordOccurrences;

import javax.swing.JTextField;
import javax.swing.JTable;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.awt.event.ActionEvent;
import static javax.swing.JOptionPane.showMessageDialog;
import static javax.swing.JOptionPane.showConfirmDialog;

import java.awt.Frame;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JToolBar;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.ImageIcon;

public class Buscador {

	private JFrame frmBuscadorDePalabras;
	private JTextField tfWord;
	private JButton btnNewButton;
	private JButton btnReadFolder;
	private FileIndex index;
	private JScrollPane scrollPane;
	private JTable results;

	private JLabel lbFolder;
	private JPanel panel_3;
	private JPanel panel_4;
	private JLabel lbFolderSelected;
	private JLabel lblNewLabel_1;
	private JLabel lbTotArchivos;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lbTotLienas;
	private JLabel lbTotPalabras;
	private JLabel lblNewLabel_6;
	private JLabel lbTotPalValidas;
	private boolean folderSelected = false;
	FinderConfiguration fconf;
	private JSeparator separator_1;
	private JSeparator separator;
	private JLabel lblNewLabel_4;
	private JLabel lbPalOmmit;
	private JList<String> lstPalOmmitted;

	/**
	 * Launch the application.
	 */
	public static void show(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Buscador window = new Buscador();
					window.frmBuscadorDePalabras.setVisible(true);
					window.readFolder();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Buscador() {
		try {
			fconf = new FinderConfiguration();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBuscadorDePalabras = new JFrame();
		frmBuscadorDePalabras.setExtendedState(Frame.MAXIMIZED_BOTH);
		frmBuscadorDePalabras.setTitle("Buscador de palabras");
		frmBuscadorDePalabras.setBounds(100, 100, 810, 300);
		frmBuscadorDePalabras.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBuscadorDePalabras.getContentPane().setLayout(new MigLayout("", "[grow,fill][:150px:280px]", "[grow]"));
		
		panel_3 = new JPanel();
		frmBuscadorDePalabras.getContentPane().add(panel_3, "flowx,cell 0 0,grow");
		panel_3.setLayout(new MigLayout("", "[][][grow]", "[][grow]"));
		
		JLabel lblNewLabel = new JLabel("Palabra a buscar");
		panel_3.add(lblNewLabel, "cell 0 0");
		
		tfWord = new JTextField();
		tfWord.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					findWord();
			}
		});
		panel_3.add(tfWord, "cell 1 0");
		tfWord.setColumns(10);
		
		btnNewButton = new JButton("Buscar");
		panel_3.add(btnNewButton, "cell 2 0");
		
		scrollPane = new JScrollPane();
		panel_3.add(scrollPane, "cell 0 1 3 1,grow");
		
		results = new JTable();
		results.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nro. ocurrencias", "Archivo"
			}
		));
		scrollPane.setViewportView(results);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findWord();
				
			}
		});
		
		panel_4 = new JPanel();
		frmBuscadorDePalabras.getContentPane().add(panel_4, "cell 1 0,grow");
		
		lbFolder = new JLabel("Carpeta seleccionada");
		
		lblNewLabel_2 = new JLabel("Total de archivos");
		
		lbTotArchivos = new JLabel("0");
		
		lblNewLabel_3 = new JLabel("Total de líneas");
		
		lbTotLienas = new JLabel("0");
		
		lblNewLabel_1 = new JLabel("Total de palabras");
		
		lbTotPalabras = new JLabel("0");
		
		lblNewLabel_6 = new JLabel("Total palabras válidas");
		
		lbTotPalValidas = new JLabel("0");
		panel_4.setLayout(new MigLayout("", "[175px,grow][4px][][114px]", "[16px][][][16px][16px][16px][16px][][][][grow]"));
		panel_4.add(lbFolder, "cell 0 0,alignx left,aligny center");
		
		lbFolderSelected = new JLabel("< No seleccionada >");
		panel_4.add(lbFolderSelected, "cell 0 1 4 1,alignx left,aligny center");
		
		separator_1 = new JSeparator();
		panel_4.add(separator_1, "cell 0 2 4 1,growx");
		panel_4.add(lblNewLabel_2, "cell 0 3,alignx left,aligny top");
		panel_4.add(lbTotArchivos, "cell 3 3,alignx right,aligny top");
		panel_4.add(lblNewLabel_3, "cell 0 4,alignx left,aligny top");
		panel_4.add(lbTotLienas, "cell 3 4,alignx right,aligny top");
		panel_4.add(lblNewLabel_1, "cell 0 5,alignx left,aligny top");
		panel_4.add(lbTotPalabras, "cell 3 5,alignx right,aligny top");
		panel_4.add(lblNewLabel_6, "cell 0 6,alignx left,aligny top");
		panel_4.add(lbTotPalValidas, "cell 3 6,alignx right,aligny top");
		
		btnReadFolder = new JButton("Cargar...");
		btnReadFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				readFolder();
			}
		});
		panel_4.add(btnReadFolder, "cell 3 0 3 1,alignx right");
		
		separator = new JSeparator();
		panel_4.add(separator, "cell 0 7 4 1,growx");
		
		lblNewLabel_4 = new JLabel("Palabras omitidas");
		panel_4.add(lblNewLabel_4, "cell 0 8");
		
		lbPalOmmit = new JLabel("--");
		panel_4.add(lbPalOmmit, "cell 0 9 4 1,alignx left");
		
		lstPalOmmitted = new JList<String>();
		panel_4.add(lstPalOmmitted, "cell 0 10 4 1,grow");
		
		if (fconf.fileWordsOmmitedExists()) 
			lbPalOmmit.setText("Palabras obtenidas desde el archivo");
		else {
			lbPalOmmit.setToolTipText("Si desea cargarlo desde el disco, cree un archivo de texto en: \n" + fconf.getFileWordsOmmited());
			lbPalOmmit.setText("Palabras obtenidas desde un array (i)" );
		} 
			
		
		
		lstPalOmmitted.setModel(getWordsOmmited());
		
//		readFolder();
	}
	
	protected void findWord() {
		results.setModel(new FindWordResultModel(new ArrayList<>()));
		if (!folderSelected) {
			showMessageDialog(frmBuscadorDePalabras, "Antes de realizar una búsqueda debe seleccionar una carpeta con archivos de texto desde la opción \""+ btnReadFolder.getText() +"\"");
			return;
		}
		
		if (fconf.getWordsOmmited().isOmmited(tfWord.getText())) {
			showMessageDialog(null, "La palabra que ha ingresado está dentro de la lista de palabras omitidas");
			return;
		}
			
		List<WordOccurrences> wordResult = index.findWords(tfWord.getText());
		results.setModel(new FindWordResultModel(wordResult));
		results.getTableHeader().repaint();
		if (wordResult.size() == 0)
			showMessageDialog(null, "No se encontraron coincidencias");
	}

	private void readFolder() {
		JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
        fc.setDialogTitle("Seleccione la carpeta con los archivos de texto");
        if (fc.showSaveDialog(frmBuscadorDePalabras) == JFileChooser.APPROVE_OPTION) {        	
        	
        	Folder f = new Folder(new File(fc.getSelectedFile().toString()), fconf);
			try {				
				index = new FileIndex(f, fconf, new WordOccurrenceList(new HashMap<>()));
				
				boolean loaded;
				try {
					loaded = index.load();					
				}catch(Exception e) {
					loaded = false;
					showMessageDialog(frmBuscadorDePalabras, "No se pudo cargar el índice. Error:\n" + e.getMessage(), "Buscador", JOptionPane.ERROR_MESSAGE);
				}
				
				if (loaded) {
					if (showConfirmDialog(frmBuscadorDePalabras,"¿Desea actualizar el índice?", "Buscador", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
						index.build();
						try {							
							index.save();
						}catch(Exception e) {
							showMessageDialog(frmBuscadorDePalabras, "No se pudo guardar el índice. Error:\n" + e.getMessage(), "Buscador", JOptionPane.ERROR_MESSAGE);
						}
					}
				} else {
					index.build();
					if (showConfirmDialog(frmBuscadorDePalabras,"¿Desea guardar el índice?", "Buscador", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
						try {							
							index.save();
						}catch(Exception e) {
							showMessageDialog(frmBuscadorDePalabras, "No se pudo guardar el índice. Error:\n" + e.getMessage(), "Buscador", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				
				lbFolderSelected.setText(fc.getSelectedFile().toString());
				lbTotArchivos.setText(Integer.toString(index.getFilesCount()));
				lbTotLienas.setText(Integer.toString(index.getRowsCount()));
				lbTotPalabras.setText(Integer.toString(index.getTotalWordsCount()));
				lbTotPalValidas.setText(Integer.toString(index.getValidWordsCount()));
				
				folderSelected = true;
				showMessageDialog(null, "Escaneo de carpeta finalizado");
				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
	}

	
	private DefaultListModel<String> getWordsOmmited() {
		DefaultListModel<String> model = new DefaultListModel<>();
		model.addAll(fconf.getWordsOmmited().getWords());
		return model;
	}
}
