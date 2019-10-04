package services;

import com.vdurmont.emoji.EmojiParser;

public class Emoji {

    public static String replaceCarTypeWithEmoji(String brandWithType) {
        brandWithType = brandWithType.trim();
        String type = brandWithType.substring(brandWithType.indexOf('(') + 1, brandWithType.lastIndexOf(')'));
        String brand = brandWithType.replaceAll("\\(" + type + "\\)", "");
        System.out.println(type);
        System.out.println(brand);

        String emojiType = "";
        switch (type.toLowerCase()) {
            case "легковий автотранспорт":
                emojiType = EmojiParser.parseToUnicode(":oncoming_automobile:");
                break;
            case "мопед":
            case "мотоцикл":
            case "моторолер":
                emojiType = EmojiParser.parseToUnicode(":motorbike:");
                break;
            case "вантажний автотранспорт":
                emojiType = EmojiParser.parseToUnicode(":truck:");
                break;
            case "тракторний причіп":
            case "трактор":
                emojiType = EmojiParser.parseToUnicode(":tractor:");
                break;
            case "автобус (мікроавтобус)":
                emojiType = EmojiParser.parseToUnicode(":minibus:");
                brand = brandWithType.replaceAll("\\(Автобус \\(мікроавтобус\\)\\)", "");
                break;
            default:
                emojiType = EmojiParser.parseToUnicode(":rocket:");
        }

        return emojiType + " " + brand;
    }
}
