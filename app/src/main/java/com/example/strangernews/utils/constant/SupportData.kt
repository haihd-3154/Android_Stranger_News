package com.example.strangernews.utils.constant

import com.example.strangernews.data.model.DataType

object SupportData {
    val categories = listOf<DataType>(
        DataType(
            id = "general",
            name = "General",
            type = TypeOfSource.CATEGORY.name,
            iconFromResource = ResourceIcon.GENERAL,
            colorFromResource = ResourceColor.RED
        ),
        DataType(
            id = "business",
            name = "Business",
            type = TypeOfSource.CATEGORY.name,
            iconFromResource = ResourceIcon.BUSINESS,
            colorFromResource = ResourceColor.PURPLE
        ),
        DataType(
            id = "entertainment",
            name = "Entertainment",
            type = TypeOfSource.CATEGORY.name,
            iconFromResource = ResourceIcon.ENTERTAINMENT,
            colorFromResource = ResourceColor.YELLOW
        ),
        DataType(
            id = "health",
            name = "Health",
            type = TypeOfSource.CATEGORY.name,
            iconFromResource = ResourceIcon.HEALTH,
            colorFromResource = ResourceColor.BLUE
        ),
        DataType(
            id = "science",
            name = "Science",
            type = TypeOfSource.CATEGORY.name,
            iconFromResource = ResourceIcon.SCIENCE,
            colorFromResource = ResourceColor.PINK
        ),
        DataType(
            id = "sports",
            name = "Sports",
            type = TypeOfSource.CATEGORY.name,
            iconFromResource = ResourceIcon.SPORTS,
            colorFromResource = ResourceColor.NAVY
        ),
        DataType(
            id = "technology",
            name = "Technology",
            type = TypeOfSource.CATEGORY.name,
            iconFromResource = ResourceIcon.TECHNOLOGY,
            colorFromResource = ResourceColor.ORANGE
        )
    )

    val sources = listOf<DataType>(
        DataType(
            id = "cnn",
            name = "CNN",
            type = TypeOfSource.SOURCE.name,
            image = "https://cdn.cnn.com/cnn/.e/img/3.0/global/misc/cnn-logo.png"
        ),
        DataType(
            id = "bbc",
            name = "BBC",
            type = TypeOfSource.SOURCE.name,
            image = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4e/BBC_News_2022_%28Alt%2C_boxed%29.svg/800px-BBC_News_2022_%28Alt%2C_boxed%29.svg.png"
        ),
        DataType(
            id = "nytimes",
            name = "The New York Times",
            type = TypeOfSource.SOURCE.name,
            image = "https://play-lh.googleusercontent.com/gfmioo4VBEtPucdVNIYAyaqruXFRWDCc0nsBLORfOS0_s9r5r00Bn_IpjhCumkEusg"
        ),
        DataType(
            id = "foxnews",
            name = "FOX News - Entertainment",
            type = TypeOfSource.SOURCE.name,
            image = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/67/Fox_News_Channel_logo.svg/2048px-Fox_News_Channel_logo.svg.png"
        ),
        DataType(
            id = "tmz",
            name = "TMZ Staff",
            type = TypeOfSource.SOURCE.name,
            image = "https://i.pinimg.com/564x/b5/44/79/b54479ee3f7699c22032f68a00d62e37.jpg"
        ),
        DataType(
            id = "espn",
            name = "ESPN",
            type = TypeOfSource.SOURCE.name,
            image = "https://static.wixstatic.com/media/0d1eb2_a4a40256e443404597d9454fa89921a5~mv2.jpg/v1/fill/w_300,h_300,al_c,q_90/file.jpg"
        ),
        DataType(
            id = "skynews",
            name = "Sky News - UK",
            type = TypeOfSource.SOURCE.name,
            image = "https://www.survation.com/wp-content/uploads/2014/02/large-square-sky-news-logo.png"
        ),
        DataType(
            id = "guardian",
            name = "The Guardian",
            type = TypeOfSource.SOURCE.name,
            image = "http://www.logo-designer.co/wp-content/uploads/2018/01/2018-The-Guardian-logo-design.png"
        ),
        DataType(
            id = "time",
            name = "TIME.com",
            type = TypeOfSource.SOURCE.name,
            image = "https://api.time.com/wp-content/themes/time2014/img/time-logo-og.png"
        ),
    )
    val countries = listOf<DataType>(
        DataType(
            id = "ar",
            name = "Argentina",
            type = TypeOfSource.SOURCE.name,
            image = "https://www.worldometers.info/img/flags/ar-flag.gif"
        ),
        DataType(
            id = "au",
            name = "Australia",
            type = TypeOfSource.SOURCE.name,
            image = "https://www.worldometers.info/img/flags/as-flag.gif"
        ),
        DataType(
            id = "be",
            name = "Belgium",
            type = TypeOfSource.SOURCE.name,
            image = "https://www.worldometers.info/img/flags/be-flag.gif"
        ),
        DataType(
            id = "br",
            name = "Brazil",
            type = TypeOfSource.SOURCE.name,
            image = "https://www.worldometers.info/img/flags/br-flag.gif"
        ),
        DataType(
            id = "ca",
            name = "Canada",
            type = TypeOfSource.SOURCE.name,
            image = "https://www.worldometers.info/img/flags/ca-flag.gif"
        ),
        DataType(
            id = "cn",
            name = "China",
            type = TypeOfSource.SOURCE.name,
            image = "https://www.worldometers.info/img/flags/ch-flag.gif"
        ),
        DataType(
            id = "eg",
            name = "Egypt",
            type = TypeOfSource.SOURCE.name,
            image = "https://www.worldometers.info/img/flags/eg-flag.gif"
        ),
        DataType(
            id = "fr",
            name = "France",
            type = TypeOfSource.SOURCE.name,
            image = "https://www.worldometers.info/img/flags/fr-flag.gif"
        ),
        DataType(
            id = "de",
            name = "Germany",
            type = TypeOfSource.SOURCE.name,
            image = "https://www.worldometers.info/img/flags/gm-flag.gif"
        ),
        DataType(
            id = "id",
            name = "Indonesia",
            type = TypeOfSource.SOURCE.name,
            image = "https://www.worldometers.info/img/flags/id-flag.gif"
        ),
        DataType(
            id = "ie",
            name = "Ireland",
            type = TypeOfSource.SOURCE.name,
            image = "https://www.worldometers.info/img/flags/ei-flag.gif"
        ),
        DataType(
            id = "il",
            name = "Israel",
            type = TypeOfSource.SOURCE.name,
            image = "https://www.worldometers.info/img/flags/is-flag.gif"
        ),
        DataType(
            id = "it",
            name = "Italy",
            type = TypeOfSource.SOURCE.name,
            image = "https://www.worldometers.info/img/flags/it-flag.gif"
        ),
        DataType(
            id = "jp",
            name = "Japan",
            type = TypeOfSource.SOURCE.name,
            image = "https://www.worldometers.info/img/flags/ja-flag.gif"
        ),
        DataType(
            id = "lv",
            name = "Latvia",
            type = TypeOfSource.SOURCE.name,
            image = "https://www.worldometers.info/img/flags/lg-flag.gif"
        ),
        DataType(
            id = "mx",
            name = "Mexico",
            type = TypeOfSource.SOURCE.name,
            image = "https://www.worldometers.info/img/flags/mx-flag.gif"
        ),
        DataType(
            id = "nl",
            name = "New Zealand",
            type = TypeOfSource.SOURCE.name,
            image = "https://www.worldometers.info/img/flags/nz-flag.gif"
        ),
        DataType(
            id = "ph",
            name = "Philippines",
            type = TypeOfSource.SOURCE.name,
            image = "https://www.worldometers.info/img/flags/rp-flag.gif"
        ),
        DataType(
            id = "pl",
            name = "Poland",
            type = TypeOfSource.SOURCE.name,
            image = "https://www.worldometers.info/img/flags/rp-flag.gif"
        ),
        DataType(
            id = "pt",
            name = "Portugal",
            type = TypeOfSource.SOURCE.name,
            image = "https://www.worldometers.info/img/flags/po-flag.gif"
        ),
        DataType(
            id = "ro",
            name = "Romania",
            type = TypeOfSource.SOURCE.name,
            image = "https://www.worldometers.info/img/flags/ro-flag.gif"
        ),
        DataType(
            id = "sa",
            name = "Saudi Arabia",
            type = TypeOfSource.SOURCE.name,
            image = "https://www.worldometers.info/img/flags/sa-flag.gif"
        ),
        DataType(
            id = "sg",
            name = "Singapore",
            type = TypeOfSource.SOURCE.name,
            image = "https://www.worldometers.info/img/flags/sn-flag.gif"
        ),
        DataType(
            id = "za",
            name = "South Africa",
            type = TypeOfSource.SOURCE.name,
            image = "https://www.worldometers.info/img/flags/sf-flag.gif"
        ),
        DataType(
            id = "kr",
            name = "South Korea",
            type = TypeOfSource.SOURCE.name,
            image = "https://www.worldometers.info/img/flags/ks-flag.gif"
        ),
        DataType(
            id = "ch",
            name = "Switzerland",
            type = TypeOfSource.SOURCE.name,
            image = "https://www.worldometers.info/img/flags/sz-flag.gif"
        ),
        DataType(
            id = "tr",
            name = "Turkey",
            type = TypeOfSource.SOURCE.name,
            image = "https://www.worldometers.info/img/flags/tu-flag.gif"
        ),
        DataType(
            id = "ae",
            name = "UAE",
            type = TypeOfSource.SOURCE.name,
            image = "https://www.worldometers.info/img/flags/ae-flag.gif"
        ),
        DataType(
            id = "gb",
            name = "United Kingdom",
            type = TypeOfSource.SOURCE.name,
            image = "https://www.worldometers.info/img/flags/uk-flag.gif"
        ),
        DataType(
            id = "us",
            name = "United States",
            type = TypeOfSource.SOURCE.name,
            image = "https://www.worldometers.info/img/flags/us-flag.gif"
        ),
        DataType(
            id = "ve",
            name = "Venezuela",
            type = TypeOfSource.SOURCE.name,
            image = "https://www.worldometers.info/img/flags/ve-flag.gif"
        ),
        DataType(
            id = "th",
            name = "Thailand",
            type = TypeOfSource.SOURCE.name,
            image = "https://www.worldometers.info/img/flags/th-flag.gif"
        ),
        DataType(
            id = "ua",
            name = "Ukraine",
            type = TypeOfSource.SOURCE.name,
            image = "https://www.worldometers.info/img/flags/up-flag.gif"
        ),

        DataType(
            id = "at",
            name = "Austria",
            type = TypeOfSource.SOURCE.name,
            image = "https://www.worldometers.info/img/flags/au-flag.gif"
        ),
        DataType(
            id = "co",
            name = "Colombia",
            type = TypeOfSource.SOURCE.name,
            image = "https://www.worldometers.info/img/flags/co-flag.gif"
        ),
        DataType(
            id = "bg",
            name = "Bulgaria",
            type = TypeOfSource.SOURCE.name,
            image = "https://www.worldometers.info/img/flags/bu-flag.gif"
        ),
        DataType(
            id = "cz",
            name = "Czech Republic",
            type = TypeOfSource.SOURCE.name,
            image = "https://www.worldometers.info/img/flags/ez-flag.gif"
        ),
        DataType(
            id = "rs",
            name = "Serbia",
            type = TypeOfSource.SOURCE.name,
            image = "https://www.worldometers.info/img/flags/ri-flag.gif"
        ),
        DataType(
            id = "in",
            name = "India",
            type = TypeOfSource.SOURCE.name,
            image = "https://www.worldometers.info/img/flags/in-flag.gif"
        ),
        DataType(
            id = "no",
            name = "Norway",
            type = TypeOfSource.SOURCE.name,
            image = "https://www.worldometers.info/img/flags/no-flag.gif"
        ),
        DataType(
            id = "gr",
            name = "Greece",
            type = TypeOfSource.SOURCE.name,
            image = "https://www.worldometers.info/img/flags/gr-flag.gif"
        ),
    )

    val languages = listOf<DataType>(
        DataType(
            id = "ar",
            name = "Arabic",
            type = TypeOfSource.LANGUAGE.name,
        ),
        DataType(
            id = "de",
            name = "German",
            type = TypeOfSource.LANGUAGE.name,
        ),
        DataType(
            id = "en",
            name = "English",
            type = TypeOfSource.LANGUAGE.name,
        ),
        DataType(
            id = "es",
            name = "Spanish",
            type = TypeOfSource.LANGUAGE.name,
        ),
        DataType(
            id = "fr",
            name = "French",
            type = TypeOfSource.LANGUAGE.name,
        ),
        DataType(
            id = "he",
            name = "Hebrew",
            type = TypeOfSource.LANGUAGE.name,
        ),
        DataType(
            id = "it",
            name = "Italian",
            type = TypeOfSource.LANGUAGE.name,
        ),
        DataType(
            id = "nl",
            name = "Dutch",
            type = TypeOfSource.LANGUAGE.name,
        ),
        DataType(
            id = "no",
            name = "Norwegian",
            type = TypeOfSource.LANGUAGE.name,
        ),
        DataType(
            id = "pt",
            name = "Portuguese",
            type = TypeOfSource.LANGUAGE.name,
        ),
        DataType(
            id = "ru",
            name = "Russian",
            type = TypeOfSource.LANGUAGE.name,
        ),
        DataType(
            id = "se",
            name = "Swedish",
            type = TypeOfSource.LANGUAGE.name,
        ),
        DataType(
            id = "zh",
            name = "Chinese",
            type = TypeOfSource.LANGUAGE.name,
        )
    )
}
