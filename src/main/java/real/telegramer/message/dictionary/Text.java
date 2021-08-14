package real.telegramer.message.dictionary;

public enum Text {
    WELCOME("Вас приветствует команда Настоящего Телеграмщика!\n" +
            "В этом боте вы можете заказать наши услуги, узнать подробнее о нас " +
            "и получить абсолютно бесплатную консультацию от ведущего специалиста."),
    CHOOSE_OPTION("Выберите нужный пункт меню."),
    ABOUT_US("В данном разделе вы можете узнать всю информацию о проекте НТ"),
    FEEDBACK("Нажмите, чтобы перейти на сайт с отзывами"),
    EDUCATION("Нажмите, чтобы перейти на сайт с информацией об обучении"),
    DESIGN("В файле по ссылке вы можете найти примеры оформления"),
    ORDER_ANSWER("Спасибо, информация была отправлена, менеджер свяжется в ближайшее время"),
    PROJECTS_BY_KEY("Нажмите, чтобы перейти на сайт с информацией о проекте под ключ"),
    BOTS("Нравится бот? Можем сделать для вас такого же. И даже лучше :)"),
    ORDER("Выберите предпочтительный способ связи"),
    WRITE_ME("Постарайтесь как можно конкретнее описать задачу: ожидания, идеи, цели."),
    PROJECTS("Выше представлена только небольшая часть наших проектов"),
    HARD("Если у вас возникли затруднения с описанием ТЗ, мы можем перезвонить вам " +
            "и наш эксперт обязательно поможет её сформулировать."),
    OK("Нажмите, чтобы связаться с нашим специалистом"),
    RECALL_ME("Мы рады Вам помочь!\n" +
            "Отправьте боту следующую информацию и мы свяжемся с вами:\n" +
            "\n" +
            "- Телефон\n" +
            "- Имя\n" +
            "- Удобное время и дату\n"),
    SERVICES("С нами можно не только сэкономить кучу времени и денег, но еще и заработать!\n" +
            "\n" +
            "Если возникнут трудности, мы всегда готовы проконсультировать вас бесплатно!"),
    OTHER("\n" +
            "◼️ [Администрирование](https://telegra.ph/143344-08-13) \n" +
            "◼️ [Копирайтинг](https://telegra.ph/Zapolnite-kanal-postami-kotorye-obespechat-maksimalnuyu-vovlechennost-08-13) \n" +
            "◼️ [Курирование](https://telegra.ph/Kurirovanie-biznesa-v-Telegram-chto-ehto-takoe-i-zachem-nuzhno-08-13) \n" +
            "◼️ [Создание креативов](https://telegra.ph/Sozdanie-kreativov-kak-deshevo-privlech-podpischikov-na-kanal-08-13)\n" +
            "◼️ [Создание лендингов](https://telegra.ph/EHffektivnaya-reklama-s-pomoshchyu-lendinga-08-13)\n" +
            "◼️ [Создание сайтов](https://telegra.ph/Sajt-kak-ehffektivnyj-instrument-dlya-biznesa-08-13)\n" +
            "◼️ [Создание воронки продаж в ботах и каналах](https://telegra.ph/Voronki-prodazh-v-botah-i-kanalah-08-13) \n" +
            "◼️ [Личные консультации и встречи в Москве](https://telegra.ph/Lichnye-konsultacii-i-vstrechi-v-Moskve-08-13) \n" +
            "◼️ [Создание рекламных роликов](https://telegra.ph/Sozdanie-prodayushchih-reklamnyh-rolikov-08-13)\n" +
            "◼️ [Исследование рынка](https://telegra.ph/Issledovanie-rynka-gotovim-ploshchadku-dlya-prodvizheniya-produkta-08-14)\n" +
            "◼️ [Исследование целевой аудитории](https://telegra.ph/Izuchaem-celevuyu-auditoriyu-08-14)\n" +
            "◼️ [Анализ канала](https://telegra.ph/Analiz-kanala-kogda-i-zachem-on-nuzhen-08-14)\n" +
            "◼️ [Продвижение в Telegram каналах](https://telegra.ph/Prodvizhenie-v-Telegram-kanalah-08-14)\n" +
            "◼️ [Таргетированная реклама](https://telegra.ph/Targetirovannaya-reklama-08-14)\n" +
            "◼️ [Реклама у блогеров](https://telegra.ph/Reklama-u-blogerov-08-14)\n" +
            "◼️ [Чистка канала от ботов](https://telegra.ph/Ochistka-kanala-ot-botov-08-14)\n" +
            "◼️ [Борьба с мошенниками](https://telegra.ph/Moshenniki-v-Telegram-chem-opasny-i-kak-s-nimi-borotsya-08-14)\n" +
            "◼️ [Запуск инфопродукта](https://telegra.ph/Zapusk-infoprodukta-08-14)\n"),
    NOT_KNOW("Простите, наш бот еще не умеет отвечать на такой запрос. Мы доработаем :)" +
            "\nНа всякий случай переслали ваш запрос менеджеру."),
    NOTIFICATION_ANONYMOUS("Пользователь без ника оставил следующее сообщение в разделе %s: %s"),
    NOTIFICATION("Пользователь c ником @%s оставил следующее сообщение в разделе %s: %s"),
    UNKNOWN_NOTIFICATION("Пользователь c ником @%s оставил следующее сообщение: %s"),
    UNKNOWN_ANONYMOUS_NOTIFICATION("Пользователь без ника оставил следующее сообщение: %s");

    private final String text;

    Text(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
