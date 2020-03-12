package exc.service;

import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

import exc.mapper.DateMapper;
import exc.model.Computer;

public class Page {
	enum selector {
		Main,
		Continue,
		Stop
		
	}
	private int nb_Pages=10;
	
	/**
	 * Method for a Pager class that allow the user to parse the list of Computer 
	 * 
	 * @param lcomp List of computer to be paged
	 */
	public void Following(List<Computer> lcomp) 
	{
		int read_count  = 0 ;
		int listCount = lcomp.size();
		for(Computer i :  lcomp)
		{
			try {
				System.out.println("id : "+i.getId()+" name : " +i.getName() +" |start : " + DateMapper.DateConverter(i.getIntroduced()) + " end : " + DateMapper.DateConverter(i.getDiscontinued()) + " | Company :  " + i.getCompany().getName() );
				read_count++;
				if (read_count == nb_Pages)
					if(Scrolling_Menu())
					{
						read_count=0;
					}
					else
					{
						return;
					}
			} catch (ParseException e2) {
				e2.printStackTrace();
				
			}
		}
		
		
		return;
	}
	
	
	/**
	 * Method that allow the user to choose if he wish to continue or not
	 * 
	 * @return if the user wishes to continue or to end 
	 */
	public boolean Scrolling_Menu ()
	{
		selector sta;
		sta = selector.Main;
		System.out.println("1 - Continue \n2 - Exit");	
		while(true)
		{
			Scanner reader = new Scanner(System.in);
			int s = reader.nextInt();
			//reader.close();
			System.out.println(s);
			if (s == 1)
			{
				sta = selector.Continue; 
			}
			else if (s== 2)
			{
				sta = selector.Stop; 
			}
			switch (sta) {
			case Stop:
				return false;
			case Continue:
				return true;
		
			default:
				System.out.println("Error");	
				break;
			}
	
		}
	}
	

}
