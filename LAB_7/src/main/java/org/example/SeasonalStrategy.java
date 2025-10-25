package org.example;

// Стратегия сезонной пересадки
public class SeasonalStrategy implements TransplantStrategy {
    private String currentSeason;

    public SeasonalStrategy(String season) {
        this.currentSeason = season;
    }

    @Override
    public boolean shouldTransplant(Trees tree) {
        if (!currentSeason.equals("осень") && !currentSeason.equals("весна")) {
            return false;
        }
        return tree.getAge() <= 10 && tree.getHealth() > 50;
    }

    @Override
    public String getReason() {
        return "Пересадка рекомендуется только весной или осенью для деревьев до 10 лет";
    }
}
