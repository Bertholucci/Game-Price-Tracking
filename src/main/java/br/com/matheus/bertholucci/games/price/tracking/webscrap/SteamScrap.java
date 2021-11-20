package br.com.matheus.bertholucci.games.price.tracking.webscrap;

import br.com.matheus.bertholucci.games.price.tracking.model.Game;
import com.jayway.jsonpath.internal.function.numeric.Max;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class SteamScrap {

    public String getDataFromSteam() throws Exception {
        List<Game> listGamesForSale = new ArrayList<>();

        Integer auxCountMaxOfGamesOnSale = 1;

        try {
            Thread.sleep(300000);
            String urlSteamSale = "https://store.steampowered.com/search/?specials=1";
            Document document = Jsoup.connect(urlSteamSale).get();

            while(auxCountMaxOfGamesOnSale < 1000){
                Elements MaxPriceObject = document.select("#search_resultsRows > a:nth-child(" + auxCountMaxOfGamesOnSale + ") > div.responsive_search_name_combined > div.col.search_price_discount_combined.responsive_secondrow > div.col.search_price.discounted.responsive_secondrow > span");
                Elements MinPriceObject = document.select("#search_resultsRows > a:nth-child(" + auxCountMaxOfGamesOnSale + ") > div.responsive_search_name_combined > div.col.search_price_discount_combined.responsive_secondrow > div.col.search_price.discounted.responsive_secondrow");
                Elements NameObject = document.select("#search_resultsRows > a:nth-child(" + auxCountMaxOfGamesOnSale + ") > div.responsive_search_name_combined > div.col.search_name.ellipsis > span");
                Elements PercentageObject = document.select("#search_resultsRows > a:nth-child(" + auxCountMaxOfGamesOnSale + ") > div.responsive_search_name_combined > div.col.search_price_discount_combined.responsive_secondrow > div.col.search_discount.responsive_secondrow > span");

                String MaxPrice = null;
                String MinPrice = null;
                String Name = null;
                String Percentage = null;

                log.info("---------------------------------------");

                if(!NameObject.isEmpty()){
                    Name = NameObject.get(0).text();
                }

                if(!PercentageObject.isEmpty()){
                    Percentage = PercentageObject.get(0).text();
                }

                if(!MaxPriceObject.isEmpty()){
                    MaxPrice = MaxPriceObject.get(0).text();
                }

                if(!MinPriceObject.isEmpty() && MinPriceObject.hasClass("discounted")){
                    MinPrice = MinPriceObject.get(0).text();
                }

                listGamesForSale.add(Game.builder()
                        .min_price(MinPrice)
                        .date(LocalDateTime.now())
                        .id(UUID.randomUUID().toString())
                        .name(Name)
                        .percentage(Percentage)
                        .build());

//                log.info("[ID]: " + listGamesForSale.get(auxCountMaxOfGamesOnSale).getId());
//                log.info("[MIN_PRICE]: " + listGamesForSale.get(auxCountMaxOfGamesOnSale).getMin_price());
//                log.info("[MAX_PRICE]: " + listGamesForSale.get(auxCountMaxOfGamesOnSale).getMax_price());
//                log.info("[NAME]: " + listGamesForSale.get(auxCountMaxOfGamesOnSale).getName());
//                log.info("[DATE]: " + listGamesForSale.get(auxCountMaxOfGamesOnSale).getDate());
//                log.info("[PERCENTAGE]: " + listGamesForSale.get(auxCountMaxOfGamesOnSale).getPercentage());

                log.info(auxCountMaxOfGamesOnSale.toString());
                auxCountMaxOfGamesOnSale++;

                if(auxCountMaxOfGamesOnSale%50 == 0){
                    Thread.sleep(60000);
                }
            }

        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            throw new IndexOutOfBoundsException("O valor procurado não pode ser encontrado na plataforma");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return"a";
    }
}
