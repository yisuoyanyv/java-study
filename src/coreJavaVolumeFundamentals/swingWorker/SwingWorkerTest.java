package coreJavaVolumeFundamentals.swingWorker;

import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

/**
 * This programe  demonstrates a worker thread that runs a potentially time-consuming task.
 * @author zhangjinglong
 * @date 2020-02-15-14:21
 */

public class SwingWorkerTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            JFrame frame=new SwingWorkerFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

/**
 * This frame has a text area to show the contents of a text file, a menu to open a file and
 * cancel the opeing process ,and a status line to show the file loading progress.
 */
class SwingWorkerFrame extends JFrame{
    private JFileChooser chooser;
    private JTextArea textArea;
    private JLabel statusLine;
    private JMenuItem openItem;
    private JMenuItem cancelItem;
    private SwingWorker<StringBuilder,ProgressData> textReader;
    public static final int TEXT_ROWS=20;
    public static final int TEXT_COLUMNS=60;

    public SwingWorkerFrame(){
        chooser=new JFileChooser();
        chooser.setCurrentDirectory(new File("."));

        textArea=new JTextArea(TEXT_ROWS,TEXT_COLUMNS);
        add(new JScrollPane(textArea));

        statusLine=new JLabel(" ");
        add(statusLine, BorderLayout.SOUTH);

        JMenuBar menuBar=new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu=new JMenu("File");
        menuBar.add(menu);

        openItem=new JMenuItem("Open");
        menu.add(openItem);
        openItem.addActionListener(event->{
            //show file chooser dialog
            int result=chooser.showOpenDialog(null);

            //if file selected,set it as icon of the label
            if(result==JFileChooser.APPROVE_OPTION){
                textArea.setText("");
                openItem.setEnabled(false);
                textReader=new TextReader(chooser.getSelectedFile());
                textReader.execute();
                cancelItem.setEnabled(true);
            }
        });

        cancelItem=new JMenuItem("Cancle");
        menu.add(cancelItem);
        cancelItem.setEnabled(false);
        cancelItem.addActionListener(event->textReader.cancel(true));
        pack();

    }


    private class ProgressData{
        public int number;
        public String line;

    }

    private class TextReader extends SwingWorker<StringBuilder,ProgressData>{
        private File file;
        private StringBuilder text=new StringBuilder();

        public TextReader(File file){
            this.file=file;
        }

        //The following method executes in the worker thread;it doesn't touch Swing components.

        @Override
        public StringBuilder doInBackground() throws IOException,InterruptedException {
            int lineNumber=0;
            try(Scanner in=new Scanner(new FileInputStream(file),"UTF-8")){
                while(in.hasNext()){
                    String line=in.nextLine();
                    lineNumber++;
                    text.append(line).append("\n");
                    ProgressData data=new ProgressData();
                    data.number=lineNumber;
                    data.line=line;
                    publish(data);
                    Thread.sleep(1);///to test cancellation;no need to do this in your programs
                }
            }

            return text;


        }

        //The following methods execute in the event dispatch thread.
        @Override
        public void process(java.util.List<ProgressData> data){
            if(isCancelled()) return;
            StringBuilder b=new StringBuilder();
            statusLine.setText(""+data.get(data.size()-1).number);
            for(ProgressData d:data) b.append(d.line).append("\n");
            textArea.append(b.toString());

        }

        @Override
        public void done(){
            try{
                StringBuilder result=get();
                textArea.setText(result.toString());
                statusLine.setText("Done");
            }catch (InterruptedException e){

            }catch (CancellationException e){
                textArea.setText("");
                statusLine.setText("Cancelled");
            }catch (ExecutionException e){
                statusLine.setText(""+e.getCause());
            }

            cancelItem.setEnabled(false);
            openItem.setEnabled(true);
        }

    }
}

