package basepackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


public class DataRaceCircuit_GUI implements Serializable{
    private JPanel jPanelMain;
    private JTextField gaddaIVA_pointsView;
    private JTextField gaddaIVD_pointsView;
    private JTextField bereniniA_pointsView;
    private JTextField bereniniB_pointsView;

    private JTextField gaddaIVA_nLapsView;
    private JTextField gaddaIVD_nLapsView;
    private JTextField bereniniA_nLapsView;
    private JTextField bereniniB_nLapsView;

    private JTextField gaddaIVA_penaltiesFullView;
    private JTextField gaddaIVD_penaltiesFullView;
    private JTextField bereniniA_penaltiesFullView;
    private JTextField bereniniB_penaltiesFullView;

    private JButton loadButton, saveBinaryButton, calculateButton;
    private JLabel squadLabel;
    private JLabel pointsLabel;
    private JLabel lapsLabel;
    private JLabel penaltiesFullLabel;
    private JLabel IVA_Label;
    private JLabel IVD_Label;
    private JLabel BereniniA_Label;
    private JLabel BereniniB_Label;
    private JButton saveCSVButton;
    private JLabel penaltiesFailedLabel;
    private JTextField gaddaIVA_penaltiesFailedView;
    private JTextField gaddaIVD_penaltiesFailedView;
    private JTextField bereniniA_penaltiesFailedView;
    private JTextField bereniniB_penaltiesFailedView;

    public DataRaceCircuit_GUI() {
        JFrame jFrame = new JFrame("Race Circuit v1.1");
        jFrame.setVisible(true);
        jFrame.setSize(1000, 200);
        jFrame.setLocation(500, 300);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        calculateButton.addActionListener(e -> {
            if (gaddaIVA_nLapsView.getText() != null &&
                    gaddaIVA_penaltiesFullView.getText() != null &&
                    gaddaIVA_penaltiesFailedView.getText() != null &&
                    gaddaIVD_nLapsView.getText() != null &&
                    gaddaIVD_penaltiesFullView.getText() != null &&
                    gaddaIVD_penaltiesFailedView.getText() != null &&
                    bereniniA_penaltiesFullView.getText() != null &&
                    bereniniA_nLapsView.getText() != null &&
                    bereniniA_penaltiesFailedView.getText() != null &&
                    bereniniB_nLapsView.getText() != null &&
                    bereniniB_penaltiesFullView.getText() != null &&
                    bereniniB_penaltiesFailedView.getText () != null) {

                int resultPoint = 50; //punteggio per ogni giro
                //numero di giri
                int resultLapsG4A = Integer.parseInt(gaddaIVA_nLapsView.getText());
                int resultLapsG4D = Integer.parseInt(gaddaIVD_nLapsView.getText());
                int resultLapsBA = Integer.parseInt(bereniniA_nLapsView.getText());
                int resultLapsBB = Integer.parseInt(bereniniB_nLapsView.getText());
                //numero di penalita completa
                int resultPenaltiesG4A = Integer.parseInt(gaddaIVA_penaltiesFullView.getText())*20;
                int resultPenaltiesG4D = Integer.parseInt(gaddaIVD_penaltiesFullView.getText())*20;
                int resultPenaltiesBA = Integer.parseInt(bereniniA_penaltiesFullView.getText())*20;
                int resultPenaltiesBB = Integer.parseInt(bereniniB_penaltiesFullView.getText())*20;
                //numero di penalita alla caduta
                int resultPenaltiesG4AFailed = Integer.parseInt(gaddaIVA_penaltiesFailedView.getText())*10;
                int resultPenaltiesG4DFailed = Integer.parseInt(gaddaIVD_penaltiesFailedView.getText())*10;
                int resultPenaltiesBereniniAFailed = Integer.parseInt(bereniniA_penaltiesFailedView.getText())*10;
                int resultPenaltiesBereniniBFailed = Integer.parseInt(bereniniB_penaltiesFailedView.getText())*10;
                //calcolo del punteggio totale
                int finalResultGadda4A = resultPoint * resultLapsG4A - resultPenaltiesG4A - resultPenaltiesG4AFailed;
                int finalResultGadda4D = resultPoint * resultLapsG4D - resultPenaltiesG4D - resultPenaltiesG4DFailed;
                int finalResultBereniniA = resultPoint * resultLapsBA - resultPenaltiesBA - resultPenaltiesBereniniAFailed;
                int finalResultBereniniB = resultPoint * resultLapsBB - resultPenaltiesBB - resultPenaltiesBereniniBFailed;

                gaddaIVA_pointsView.setText(Integer.toString(finalResultGadda4A));
                gaddaIVD_pointsView.setText(Integer.toString(finalResultGadda4D));
                bereniniA_pointsView.setText(Integer.toString(finalResultBereniniA));
                bereniniB_pointsView.setText(Integer.toString(finalResultBereniniB));
            }
        });
		//assegno al pulsante CSV Button che esporta i dati in formato *.csv, dopo di che apre la cartella dove
		//è stato salvato il file.
        saveCSVButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
				//Creo oggetto FileWriter.
                FileWriter fileWriter = null;
                try {
					//Ecco il path, cioè la destinazione
                    fileWriter = new FileWriter("C:\\Users\\Arnol'd\\Documents\\MainPath\\File.csv");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                try {
                    if (fileWriter != null) {
						//scrivo tutti i miei campi tramite metodo .getText() e concateno con ";", che corrisponde 
						//ad un formato CSV
                        fileWriter.write(squadLabel.getText() + ";" +
                                pointsLabel.getText() + ";" +
                                lapsLabel.getText() + ";" +
                                penaltiesFullLabel.getText() + ";" +
                                penaltiesFailedLabel.getText() + "\n" +
                                IVA_Label.getText() + ";" +
                                gaddaIVA_pointsView.getText() + ";" +
                                gaddaIVA_nLapsView.getText() + ";" +
                                gaddaIVA_penaltiesFullView.getText() + ";" +
                                gaddaIVA_penaltiesFailedView.getText() + "\n" +
                                IVD_Label.getText() + ";" +
                                gaddaIVD_pointsView.getText() + ";" +
                                gaddaIVD_nLapsView.getText() + ";" +
                                gaddaIVD_penaltiesFullView.getText() + ";" +
                                gaddaIVD_penaltiesFailedView.getText() + "\n" +
                                BereniniA_Label.getText() + ";" +
                                bereniniA_pointsView.getText() + ";" +
                                bereniniA_nLapsView.getText() + ";" +
                                bereniniA_penaltiesFullView.getText() + ";" +
                                bereniniA_penaltiesFailedView.getText() + "\n" +
                                BereniniB_Label.getText() + ";" +
                                bereniniB_pointsView.getText() + ";" +
                                bereniniB_nLapsView.getText() + ";" +
                                bereniniB_penaltiesFullView.getText() + ";" +
                                bereniniB_penaltiesFailedView.getText());
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                try {
                    if (fileWriter != null) {
                        fileWriter.close();
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
				//Creo oggetto Desktop e Runtime, tutto questo serve per aprile il path.
                Desktop desktop = Desktop.getDesktop();
                File dirToOpen = null;
                try {
                    String path = "C:\\Users\\Arnol'd\\Documents\\MainPath";
                    Runtime runtime = Runtime.getRuntime();
                    runtime.exec("explorer.exe "+path);
                    System.out.println("open");
                } catch (Exception E) {
                    System.out.println("File Not Found");
                }
            }
        });
		//Assegno addActionListener al buttone saveBinaryButton. 
		//Serve per salvare tutti i dati (campi) in formato HEX (una specie di formato esadecimale). 
		//Questo è tramite ObjectOutputStream e FileOutputStream
        saveBinaryButton.addActionListener(e -> {
            ObjectOutputStream objectOutputStream = null;
            try {
                objectOutputStream = new ObjectOutputStream(new FileOutputStream("C:\\Users\\Arnol'd\\Documents\\MainPath\\File.bin"));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.writeObject(gaddaIVA_pointsView.getText());
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.writeObject(gaddaIVA_nLapsView.getText());
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.writeObject(gaddaIVA_penaltiesFullView.getText());
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            try {
                if (objectOutputStream != null) {
                    objectOutputStream.writeObject(gaddaIVA_penaltiesFailedView.getText());
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            try {
                if (objectOutputStream != null) {
                    objectOutputStream.writeObject(gaddaIVD_pointsView.getText());
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.writeObject(gaddaIVD_nLapsView.getText());
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.writeObject(gaddaIVD_penaltiesFullView.getText());
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            try {
                if (objectOutputStream != null) {
                    objectOutputStream.writeObject(gaddaIVD_penaltiesFailedView.getText());
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            try {
                if (objectOutputStream != null) {
                    objectOutputStream.writeObject(bereniniA_pointsView.getText());
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.writeObject(bereniniA_nLapsView.getText());
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.writeObject(bereniniA_penaltiesFullView.getText());
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.writeObject(bereniniA_penaltiesFailedView.getText());
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            try {
                if (objectOutputStream != null) {
                    objectOutputStream.writeObject(bereniniB_pointsView.getText());
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.writeObject(bereniniB_nLapsView.getText());
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.writeObject(bereniniB_penaltiesFullView.getText());
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.writeObject(bereniniB_penaltiesFailedView.getText());
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
		//Assegno addActionListener al buttone loadButton
		//Serve per il recupero di miei dati, salvati precedentemente con OOS e FOS.
		//Ora utilizziamo FileInputStream e ObjectInputStream detti anche FIS e OIS.
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ObjectInputStream objectInputStream = null;
                try {
                    objectInputStream = new ObjectInputStream(new FileInputStream("C:\\Users\\Arnol'd\\Documents\\MainPath\\File.bin"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                try {
                    String temp = null;
                    if (objectInputStream != null) {
                        temp = (String) objectInputStream.readObject();
                    }
                    gaddaIVA_pointsView.setText(temp);
                    String temp1 = null;
                    if (objectInputStream != null) {
                        temp1 = (String) objectInputStream.readObject();
                    }
                    gaddaIVA_nLapsView.setText(temp1);
                    String temp2 = null;
                    if (objectInputStream != null) {
                        temp2 = (String) objectInputStream.readObject();
                    }
                    gaddaIVA_penaltiesFullView.setText(temp2);
                    String temp12 = null;
                    if (objectInputStream != null) {
                        temp12 = (String) objectInputStream.readObject();
                    }
                    gaddaIVA_penaltiesFailedView.setText(temp12);
                    String temp3 = null;
                    if (objectInputStream != null) {
                        temp3 = (String) objectInputStream.readObject();
                    }
                    gaddaIVD_pointsView.setText(temp3);

                    String temp4 = null;
                    if (objectInputStream != null) {
                        temp4 = (String) objectInputStream.readObject();
                    }
                    gaddaIVD_nLapsView.setText(temp4);

                    String temp5 = null;
                    if (objectInputStream != null) {
                        temp5 = (String) objectInputStream.readObject();
                    }
                    gaddaIVD_penaltiesFullView.setText(temp5);
                    String temp13 = null;
                    if (objectInputStream != null) {
                        temp13 = (String) objectInputStream.readObject();
                    }gaddaIVD_penaltiesFailedView.setText(temp13);
                    String temp6 = null;
                    if (objectInputStream != null) {
                        temp6 = (String) objectInputStream.readObject();
                    }
                    bereniniA_pointsView.setText(temp6);

                    String temp7 = null;
                    if (objectInputStream != null) {
                        temp7 = (String) objectInputStream.readObject();
                    }
                    bereniniA_nLapsView.setText(temp7);

                    String temp8 = null;
                    if (objectInputStream != null) {
                        temp8 = (String) objectInputStream.readObject();
                    }
                    bereniniA_penaltiesFullView.setText(temp8);
                    String temp14 = null;
                    if (objectInputStream != null) {
                        temp14 = (String) objectInputStream.readObject();
                    }
                    bereniniA_penaltiesFailedView.setText(temp14);
                    String temp9 = null;
                    if (objectInputStream != null) {
                        temp9 = (String) objectInputStream.readObject();
                    }
                    bereniniB_pointsView.setText(temp9);

                    String temp10 = null;
                    if (objectInputStream != null) {
                        temp10 = (String) objectInputStream.readObject();
                    }
                    bereniniB_nLapsView.setText(temp10);

                    String temp11 = null;
                    if (objectInputStream != null) {
                        temp11 = (String) objectInputStream.readObject();
                    }
                    bereniniB_penaltiesFullView.setText(temp11);
                    String temp15 = null;
                    if (objectInputStream != null) {
                        temp15 = (String) objectInputStream.readObject();
                    }bereniniB_penaltiesFailedView.setText(temp15);
                } catch (IOException | ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
                try {
                    if (objectInputStream != null) {
                        objectInputStream.close();
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        jFrame.add(jPanelMain);
    }

    public static void main(String[] args) {
        @SuppressWarnings(value = "unused")
        DataRaceCircuit_GUI dataRaceCircuit_gui = new DataRaceCircuit_GUI();
    }
}
