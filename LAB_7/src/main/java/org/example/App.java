package org.example;

public class App 
{
    public static void main( String[] args )
    {
        TreeManager garden = new TreeManager();

        garden.loadTreesFromFile("trees.txt");

        System.out.println("\n1. СТРАТЕГИЯ ДЛЯ МОЛОДЫХ ДЕРЕВЬЕВ:");
        garden.setGlobalStrategy(new YoungTreeStrategy());
        garden.checkAllTrees();

        System.out.println("\n2. СТРАТЕГИЯ ДЛЯ ЗРЕЛЫХ ПЛОДОНОСЯЩИХ ДЕРЕВЬЕВ:");
        garden.setGlobalStrategy(new MatureFruitingStrategy());
        garden.checkAllTrees();

        System.out.println("\n3. СТРАТЕГИЯ ДЛЯ СТАРЫХ ДЕРЕВЬЕВ:");
        garden.setGlobalStrategy(new OldTreeStrategy());
        garden.checkAllTrees();

        System.out.println("\n4. СЕЗОННАЯ СТРАТЕГИЯ (ОСЕНЬ):");
        garden.setGlobalStrategy(new SeasonalStrategy("осень"));
        garden.checkAllTrees();

        System.out.println("\n=== ИНДИВИДУАЛЬНЫЕ СТРАТЕГИИ ===");
        garden.setIndividualStrategy("Антоновка", new YoungTreeStrategy());
        garden.setIndividualStrategy("Дюшес", new OldTreeStrategy());

        garden.getStatistics();

        garden.saveTreesToFile("saved_trees.txt");

        System.out.println("\n=== ДОБАВЛЕНИЕ НОВОГО ДЕРЕВА ===");
        garden.addTree("Новая яблоня", "Яблоня", 1, 95, 20);

        System.out.println("\n=== ФИНАЛЬНАЯ ПРОВЕРКА ===");
        garden.checkAllTrees();
        garden.getStatistics();
    }
}
