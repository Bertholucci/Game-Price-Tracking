package br.com.matheus.bertholucci.games.price.tracking.webscrap;

import br.com.matheus.bertholucci.games.price.tracking.model.Game;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class NuuvemScrap {

    public List<Game> getDataFromNuuvem() throws Exception {
        List<Game> listGamesForSale = new ArrayList<>();

        Integer auxCountMaxOfGamesOnSale = 1;
        Integer auxPageController = 1;
        Integer registersInASinglePage = 1;

        try {
            String urlNuuvemSaleForGettingParams = "https://www.nuuvem.com/catalog/price/promo/sort/bestselling/sort-mode/desc/page/1.html";
            Document documentGeneralInformation = Jsoup.connect(urlNuuvemSaleForGettingParams).get();

            int maxResultsFound = Integer.parseInt(documentGeneralInformation.select("#catalog > div:nth-child(3) > div.products-search > div.products-search-header > div.products-search-term.grid").get(0).text().replaceAll("[a-zA-Z]", "").replace(" ",""));
            int maxPageRequests = detectMaximumPagesToRequest(maxResultsFound);

            while(auxCountMaxOfGamesOnSale <= maxResultsFound){
                if(auxCountMaxOfGamesOnSale%20 == 0){
                    registersInASinglePage = 1;
                    auxPageController++;
                }

                String urlNuuvemSaleForProductsInformation = "https://www.nuuvem.com/catalog/price/promo/sort/bestselling/sort-mode/desc/page/"+auxPageController+".html";
                Document documentProductsInformation = Jsoup.connect(urlNuuvemSaleForProductsInformation).get();

                Elements MinPriceObjectSup = documentProductsInformation.select("#catalog > div:nth-child(3) > div.products-items > div > div > div:nth-child("+registersInASinglePage+") > div > a > div.product-card--footer > div > button > span > span.integer");
                Elements MinPriceObjectDec = documentProductsInformation.select("#catalog > div:nth-child(3) > div.products-items > div > div > div:nth-child("+registersInASinglePage+") > div > a > div.product-card--footer > div > button > span > span.decimal");
                Elements NameObject = documentProductsInformation.select("#catalog > div:nth-child(3) > div.products-items > div > div > div:nth-child("+registersInASinglePage+") > div > a > div.product-card--content > div > h3");
                Elements PercentageObject = documentProductsInformation.select("#catalog > div:nth-child(3) > div.products-items > div > div > div:nth-child("+registersInASinglePage+") > div > a > div.product-card--footer > div > span");

                String MinPrice = null;
                String Name = null;
                String Percentage = null;

                if(!NameObject.isEmpty()){
                    Name = NameObject.get(0).text();
                }

                if(!MinPriceObjectSup.isEmpty() && !MinPriceObjectDec.isEmpty()){
                    MinPrice = "R$ ".concat(MinPriceObjectSup.get(0).text().concat(MinPriceObjectDec.get(0).text()));
                }

                if(!PercentageObject.isEmpty()){
                    Percentage = PercentageObject.get(0).text();
                }

                listGamesForSale.add(Game.builder()
                        .min_price(MinPrice)
                        .date(LocalDateTime.now())
                        .id(UUID.randomUUID().toString())
                        .name(Name)
                        .platform("Nuuvem")
                        .percentage(Percentage)
                        .build());

                log.info("*******************************");
                log.info("ROLETA DE NÚMERO: ["+auxCountMaxOfGamesOnSale+"]");
                log.info("[ID]: " + listGamesForSale.get(auxCountMaxOfGamesOnSale-1).getId());
                log.info("[MIN_PRICE]: " + listGamesForSale.get(auxCountMaxOfGamesOnSale-1).getMin_price());
                log.info("[NAME]: " + listGamesForSale.get(auxCountMaxOfGamesOnSale-1).getName());
                log.info("[DATE]: " + listGamesForSale.get(auxCountMaxOfGamesOnSale-1).getDate());
                log.info("[PERCENTAGE]: " + listGamesForSale.get(auxCountMaxOfGamesOnSale-1).getPercentage());
                log.info("[PLATFORM]: " + listGamesForSale.get(auxCountMaxOfGamesOnSale-1).getPlatform());
                log.info("[ENDPOINT PAGE]: " + documentProductsInformation.location());
                log.info("*******************************");

                auxCountMaxOfGamesOnSale++;
                registersInASinglePage++;
            }

        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            throw new IndexOutOfBoundsException("Ocorreu um erro na busca por valores usando webscraping" + indexOutOfBoundsException.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listGamesForSale;
    }

    public Integer detectMaximumPagesToRequest(Integer results){
        double divisionValue = (double) (results / 20);

        return (int) divisionValue + 1;
    }
}
