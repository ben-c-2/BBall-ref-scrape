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
		// TODO Auto-generated method stub
		final String url =
				"https://www.basketball-reference.com/players/e/embiijo01/gamelog/2021/";
		final String player = 
				"Joel Embiid";
		final String team =
				"Sixers";
		int inTot = 0;
		int outTot = 0;
		double gp = 0;
		double gnp = 0;
		
		try {
			final Document document = Jsoup.connect(url).get();
		
			for(Element row : document.select("tbody tr"))
			{
				if(row.select(".center:nth-of-type(7)").text().equals(""))
				{
					continue;
				}
				else {
					
					final String record =
							row.select(".center:nth-of-type(7)").text();
					final String play =
							row.select(".center:nth-of-type(8)").text();
					if(row.select(".center:nth-of-type(8)").text().equals("") && !(row.select(".center:nth-of-type(7)").text().equals("Opp")))
					{
						inTot += Integer.parseInt(record.replaceAll("[^\\-|^\\d]", ""));
						gp++;
					}
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
