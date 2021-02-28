package webscrape;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/*
 * 
 * @author Ben Chau
 * @date 2.27.2021
 */
public class WebScrape {

	public static void main(String[] args) {
		
		// URL of game log 
		
		final String url =
				"https://www.basketball-reference.com/players/e/embiijo01/gamelog/2021/";
		
		// name of player
		
		final String player = 
				"Joel Embiid";
		
		// name of team
		
		final String team =
				"Sixers";
		int inTot = 0;		// total score of games played with player
		int outTot = 0;		// total score of games played without player
		double gp = 0;		// games played
		double gnp = 0;		// games missed
		
		try {
			final Document document = Jsoup.connect(url).get();		// connects to the url
		
			for(Element row : document.select("tbody tr"))			// finds list of game logs
			{
				if(row.select(".center:nth-of-type(7)").text().equals(""))		// filters out empty rows
				{
					continue;
				}
				else {
					
					final String record =
							row.select(".center:nth-of-type(7)").text();		// retrieves win loss data
					final String play =
							row.select(".center:nth-of-type(8)").text();		// retrieves if player played
					
					// filters if player played
					if(row.select(".center:nth-of-type(8)").text().equals("") && !(row.select(".center:nth-of-type(7)").text().equals("Opp")))
					{
						inTot += Integer.parseInt(record.replaceAll("[^\\-|^\\d]", ""));
						gp++;
					}
					// filters if player did not play
					
					if(row.select(".center:nth-of-type(8)").text().contains("i"))
					{
						outTot += Integer.parseInt(record.replaceAll("[^\\-|^\\d]", ""));
						gnp++;
					}
					
				}
				
			}
			System.out.println(player + " playing with the " + team + " outscores opponents by " + inTot/gp + " on average and while not playing the " + team + " outscores opponents by " + outTot/gnp  );
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

}

}
