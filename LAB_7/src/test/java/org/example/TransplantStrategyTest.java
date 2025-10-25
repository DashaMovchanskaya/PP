package org.example;
import static org.junit.Assert.*;

import org.junit.Test;

public class TransplantStrategyTest {

    private Trees youngHealthyApple;
    private Trees matureLowYieldApple;
    private Trees oldUnhealthyPear;
    private Trees middleAgedCherry;

    private void setUp() {
        youngHealthyApple = new AppleTree("Test Young", 2, 85, 40);
        matureLowYieldApple = new AppleTree("Test Mature", 8, 65, 45);
        oldUnhealthyPear = new PearTree("Test Old", 20, 35, 30);
        middleAgedCherry = new CherryTree("Test Middle", 10, 75, 60);
    }

    @Test
    public void testYoungTreeStrategy_ShouldTransplant() {
        setUp();
        TransplantStrategy strategy = new YoungTreeStrategy();

        assertTrue(strategy.shouldTransplant(youngHealthyApple));
        assertEquals("Молодые деревья лучше адаптируются к новой почве", strategy.getReason());
    }

    @Test
    public void testYoungTreeStrategy_ShouldNotTransplant() {
        setUp();
        TransplantStrategy strategy = new YoungTreeStrategy();

        assertFalse(strategy.shouldTransplant(matureLowYieldApple));

        Trees youngUnhealthy = new AppleTree("Test", 2, 60, 40);
        assertFalse(strategy.shouldTransplant(youngUnhealthy));
    }

    @Test
    public void testMatureFruitingStrategy_ShouldTransplant() {
        setUp();
        TransplantStrategy strategy = new MatureFruitingStrategy();

        assertTrue(strategy.shouldTransplant(matureLowYieldApple));
        assertEquals("Пересадка может улучшить плодоношение зрелых деревьев", strategy.getReason());
    }

    @Test
    public void testMatureFruitingStrategy_ShouldNotTransplant() {
        setUp();
        TransplantStrategy strategy = new MatureFruitingStrategy();

        assertFalse(strategy.shouldTransplant(youngHealthyApple));
        assertFalse(strategy.shouldTransplant(oldUnhealthyPear));
        Trees highYieldTree = new AppleTree("Test", 8, 65, 70);
        assertFalse(strategy.shouldTransplant(highYieldTree));
    }

    @Test
    public void testOldTreeStrategy_ShouldTransplant() {
        setUp();
        TransplantStrategy strategy = new OldTreeStrategy();

        assertTrue(strategy.shouldTransplant(oldUnhealthyPear));
        assertEquals("Старые деревья пересаживаются только в случае плохого здоровья", strategy.getReason());
    }

    @Test
    public void testOldTreeStrategy_ShouldNotTransplant() {
        setUp();
        TransplantStrategy strategy = new OldTreeStrategy();

        assertFalse(strategy.shouldTransplant(youngHealthyApple));
        Trees oldHealthy = new AppleTree("Test", 20, 60, 40);
        assertFalse(strategy.shouldTransplant(oldHealthy));
    }

    @Test
    public void testSeasonalStrategy_Spring() {
        setUp();
        TransplantStrategy strategy = new SeasonalStrategy("весна");

        assertTrue(strategy.shouldTransplant(youngHealthyApple));
        assertEquals("Пересадка рекомендуется только весной или осенью для деревьев до 10 лет",
                strategy.getReason());
    }

    @Test
    public void testSeasonalStrategy_Autumn() {
        setUp();
        TransplantStrategy strategy = new SeasonalStrategy("осень");

        assertTrue(strategy.shouldTransplant(youngHealthyApple));
    }

    @Test
    public void testSeasonalStrategy_Winter() {
        setUp();
        TransplantStrategy strategy = new SeasonalStrategy("зима");

        assertFalse(strategy.shouldTransplant(youngHealthyApple));
    }

    @Test
    public void testSeasonalStrategy_TooOld() {
        setUp();
        TransplantStrategy strategy = new SeasonalStrategy("весна");

        assertFalse(strategy.shouldTransplant(oldUnhealthyPear));
    }
}
