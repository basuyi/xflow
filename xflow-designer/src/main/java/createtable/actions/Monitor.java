package createtable.actions;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.Timer;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;

//import org.eclipse.jface.window.Window;

public class Monitor implements IRunnableWithProgress{

 /**
  * @param args
  */
private int i=0;
private IProgressMonitor monitor;
//private Timer timer = new Timer(1000,(ActionListener) this);

	
public Monitor(){
}
 public void run(IProgressMonitor monitor) throws InvocationTargetException,
 InterruptedException
{
	
this.monitor=monitor;
monitor.beginTask("正在生成！",20);
while(true){
	if(i<20){
	Thread.sleep(10);
	monitor.worked(1);
	i++;
	}
	if(i>=20&&i<25){
		monitor.beginTask("正在生成！",20);
		i=0;
	}
	if(i>=25&&i<30){
			
		Thread.sleep(7000);
	}
	if(i>=30){
		
		this.monitor.worked(20);
		return;
	}
  }
}

 
	public void timerStop(String compname){

    	this.i=28;
    	

	}
	public void timerStart(){
	i=0;
	}
	public void monitorCancel(){

		this.i=35;
		
	}
	
	  
}






