package com.vincentwang.zoo.model

import com.google.gson.Gson
import com.vincentwang.zoo.ui.intro.IntroData
import com.vincentwang.zoo.ui.plant.PlantData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject

class ZooRepository(private val gson: Gson, private val services: ZooServices) {


    fun getPlant(number:Int):Flow<PlantData>{
        return flow {
            val offset = number*10
            val data = services.getPlantInfo(limit = 10,offset = offset)
           emit(data)
        }
    }

    fun getIntro(): Flow<IntroData> {
        return flow<IntroData> {
            val data = "{\"results\":[{\n" +
                    "\"E_no\":\"1\",\n" +
                    "\"E_Category\":\"戶外區\",\n" +
                    "\"E_Name\":\"臺灣動物區\",\n" +
                    "\"E_Pic_URL\":\"http://www.zoo.gov.tw/iTAP/05_Exhibit/01_FormosanAnimal.jpg\",\n" +
                    "\"E_Info\":\"臺灣動物區以臺灣原生動物與棲息環境為展示重點，佈置模擬動物原生棲地之生態環境，讓動物表現如野外般自然的生活習性，引導觀賞者更正確地認識本土野生動物。臺灣位處於亞熱帶，雨量充沛、氣候溫暖，擁有各種地形景觀，因而孕育了豐富龐雜的生物資源。\",\n" +
                    "\"E_Memo\":\"週一到週日\",\n" +
                    "\"E_Geo\":\"121.5805931\",\n" +
                    "\"E_URL\":\"http://www.zoo.gov.tw/introduce/gq.aspx?tid=12\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"E_no\":\"2\",\n" +
                    "\"E_Category\":\"戶外區\",\n" +
                    "\"E_Name\":\"兒童動物區\",\n" +
                    "\"E_Pic_URL\":\"http://www.zoo.gov.tw/iTAP/05_Exhibit/02_ChildrenZoo.jpg\",\n" +
                    "\"E_Info\":\"兒童動物園全區以埤塘、水田等各類濕地與郊野生態造景為環境意象，串聯農村動物、經濟動物、寵物、入侵之外來種動物等單元主題，點出人類與動物間的密切關係，提供學童、家長與老師一處共同體驗與學習的空間。\",\n" +
                    "\"E_Memo\":\"週一到週日\",\n" +
                    "\"E_Geo\":\"121.5819383\",\n" +
                    "\"E_URL\":\"http://www.zoo.gov.tw/introduce/gq.aspx?tid=12\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"E_no\":\"3\",\n" +
                    "\"E_Category\":\"戶外區\",\n" +
                    "\"E_Name\":\"熱帶雨林區\",\n" +
                    "\"E_Pic_URL\":\"http://www.zoo.gov.tw/iTAP/05_Exhibit/03_TropicalRainforest.jpg\",\n" +
                    "\"E_Info\":\"熱帶雨林區模擬東南亞熱帶雨林的自然生態景觀，依展示動線規劃成河口生態、密林生態及林緣生態三大展示區，區內可見板根、氣生根、支柱根、附生植物、林間霧氣等熱帶雨林特有的生態現象。\",\n" +
                    "\"E_Memo\":\"週一到週日\",\n" +
                    "\"E_Geo\":\"121.5834188\",\n" +
                    "\"E_URL\":\"http://www.zoo.gov.tw/introduce/gq.aspx?tid=12\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"E_no\":\"4\",\n" +
                    "\"E_Category\":\"戶外區\",\n" +
                    "\"E_Name\":\"沙漠動物區\",\n" +
                    "\"E_Pic_URL\":\"http://www.zoo.gov.tw/iTAP/05_Exhibit/04_DesertAnimal.jpg\",\n" +
                    "\"E_Info\":\"沙漠動物區以隨風搖曳的棕櫚樹模擬中東地區的沙漠環境，展示具代表性的單峰駱駝、雙峰駱駝、非洲野驢和弓角羚羊等。來到這裡，你就能知道動物用什麼方式適應乾旱、高溫及晝夜溫差大的氣候呢？\",\n" +
                    "\"E_Memo\":\"週一到週日\",\n" +
                    "\"E_Geo\":\"121.5851489\",\n" +
                    "\"E_URL\":\"http://www.zoo.gov.tw/introduce/gq.aspx?tid=12\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"E_no\":\"5\",\n" +
                    "\"E_Category\":\"戶外區\",\n" +
                    "\"E_Name\":\"澳洲動物區\",\n" +
                    "\"E_Pic_URL\":\"http://www.zoo.gov.tw/iTAP/05_Exhibit/05_AustralianAnimal.jpg\",\n" +
                    "\"E_Info\":\"澳洲動物區栽種許多澳洲特有的桉樹，在這裡可以遇到袋鼠、鴯?和食火雞。澳洲大陸長時間與其他陸塊隔離，繁衍並演化出與其他陸域不同形態的物種，值得大小朋友一同來認識牠們喔！\",\n" +
                    "\"E_Memo\":\"週一到週日\",\n" +
                    "\"E_Geo\":\"121.5853326\",\n" +
                    "\"E_URL\":\"http://www.zoo.gov.tw/introduce/gq.aspx?tid=12\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"E_no\":\"6\",\n" +
                    "\"E_Category\":\"戶外區\",\n" +
                    "\"E_Name\":\"非洲動物區\",\n" +
                    "\"E_Pic_URL\":\"http://www.zoo.gov.tw/iTAP/05_Exhibit/06_AfricanAnimal.jpg\",\n" +
                    "\"E_Info\":\"非洲動物區多採動物混群的展示方式，模擬東非莽原情境，讓參觀者在視覺上宛如置身非洲草原。非洲大陸被譽為「野生動物王國」，擁有世界最龐大的動物族群，不僅動物種類豐富，數量亦最為壯觀，快來這裡拜訪陸地上體型最大和身高最高的動物吧！\",\n" +
                    "\"E_Memo\":\"週一到週日\",\n" +
                    "\"E_Geo\":\"121.5880094\",\n" +
                    "\"E_URL\":\"http://www.zoo.gov.tw/introduce/gq.aspx?tid=12\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"E_no\":\"7\",\n" +
                    "\"E_Category\":\"戶外區\",\n" +
                    "\"E_Name\":\"溫帶動物區\",\n" +
                    "\"E_Pic_URL\":\"http://www.zoo.gov.tw/iTAP/05_Exhibit/07_TemperateZone.jpg\",\n" +
                    "\"E_Info\":\"溫帶動物區有棲息於溫帶草原和森林中的動物。地球上廣大的溫帶氣候區有沙漠、草原、落葉林及針帶林等不同生態環境，因此動物種類繁多。然而溫帶氣候也很適合人類居住，因此這裡的動物大多面臨棲息地減少或其他與人有關的生存危機，需要我們一起來關心牠們的保育。\",\n" +
                    "\"E_Memo\":\"週一到週日\",\n" +
                    "\"E_Geo\":\"121.5896013\",\n" +
                    "\"E_URL\":\"http://www.zoo.gov.tw/introduce/gq.aspx?tid=12\"\n" +
                    "},\n" +
                    "{\n" +
                    "\"E_no\":\"8\",\n" +
                    "\"E_Category\":\"戶外區\",\n" +
                    "\"E_Name\":\"鳥園區\",\n" +
                    "\"E_Pic_URL\":\"http://www.zoo.gov.tw/iTAP/05_Exhibit/08_BirdWorld.jpg\",\n" +
                    "\"E_Info\":\"鳥園區包括鳥類形態區、雉類與珍禽區、鶴園、鸚鵡房、生態鳥園及水禽區，可以觀察到住在不同棲息地的鳥類，是都會環境中難得的賞鳥據點。歡迎放慢腳步，聽聽悅耳鳥鳴、看看羽色繽紛的輕盈身影，感受另一個廣闊而自由的世界。\",\n" +
                    "\"E_Memo\":\"週一到週日\",\n" +
                    "\"E_Geo\":\"121.5888946\",\n" +
                    "\"E_URL\":\"http://www.zoo.gov.tw/introduce/gq.aspx?tid=12\"\n" +
                    "}\n" +
                    "]}"
            emit(gson.fromJson(data, IntroData::class.java))
        }

    }
}