package towerdefence.gameelements;

//Effectid töötavad umbes nii:
//iga turni lõpus käiakse üle kõik effektid ja kutsutakse välja .process() meetod igaühe peal
//kui see turn ei juhtu midagi, siis lihtsalt suurendatakse elapsedTime väärtust
//kui juhtub midagi, siis lisaks tehakse see vastav aktsioon

//seejärel käiakse UUESTI üle kõik effektid, ja kui selle aeg on läbi, eemaldatakse see
//effektide järjendist. 

import java.util.ArrayList;

public class Effect {
    
    private final Target targetType; //efekti targeti tüüp - koll? ruut? 
    private final Type damageType; //efekti tüüp - damage? slow? jne
    
    private final int totalTime; //kaua efekt kestab või kaua läheb aega efekti aktiveerimiseks
    private final int amount; //efekti "suurus" - damage, slow amount, jne
    private int elapsedTime; //kaua efekt on aktiivne olnud
    private Enemy targetEnemy; //efekti poolt targetitav enemy (kui singletarget)
    private Grid targetGrid; //efekti poolt targetitav grid (kui splash jne)
    
    /**
     * Konstruktor. 
     * 
     * @param totalTime
     * @param amount
     * @param targetType
     * @param type 
     */

    public Effect(int totalTime, int amount, Target targetType, Type type) {
        this.totalTime = totalTime;
        this.elapsedTime = 0; 
        this.amount = amount; 
        this.targetType = targetType;
        this.damageType = type; 
    }
    
    /**
     * Kutsutakse välja iga efekti jaoks iga turni lõpus.
     * Handlib efekti poolt tekitatava kahju jne, juhul kui see turn toimib. 
     */
    public void process() {
        
        //TODO - FAAS 2 - kontroll - kas tuleb praegu protsessimisele? 
        //HILJEM!!! esialgu arvestame et tuleb. 
        
        //see list hoiab nimekirja kõikidest vastastest, kes on affektitud
        ArrayList<Enemy> affectedList = new ArrayList<Enemy>();
        
        //kas single-target efekt? 
        //kui jah, lisame ainukese vastase listi
        if (this.targetType == Target.SINGLE) {
            if (targetEnemy == null) throw new AssertionError();
            affectedList.add(targetEnemy);
        }
        //kas splash efekt? kui jah, lisame kõik vastased selles ruudus listi
        else if (this.targetType == Target.SPLASH) {
            for (Object enemy : targetGrid.getEnemyList()) {
                affectedList.add((Enemy)enemy); //TODO - test - võib katki minna? 
                //TODO - FAAS 2 - lisa ka külgmistest ruutudest vms?
                //või peaks see üldse olema eraldi efekt mis juba varem on välja arvutatud? 
            }
        }
        else throw new AssertionError(); //vale targetType kuidagi määratud?
        
        //kas see efekt teeb lihtsalt haiget? 
        if (this.damageType == Type.DAMAGE) {
            for (Enemy enemy : affectedList) {
                enemy.damage(this.amount);
            }                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
        }
                
        else if (this.damageType == Type.SLOW) {
            //TODO - FAAS 2
            //slowi kõiki kes affectedListis
            //tähendab et pane neile mingi teine effect külge.. 
        }
        
        else throw new AssertionError(); //vale damageType määratud? 
        
        //suurendame efekti aktiivsusaega.. 
        increaseElapsedTime(); 
    }
    
    /**
     * Määrame efekti targetiks oleva kolli (kui on koll).   
     * @param enemy 
     */
    public void setTargetEnemy(Enemy enemy) {
        this.targetEnemy = enemy;
    }
    
    /**
     * Tagastab efekti targetiks oleva kolli. 
     * Kui efekti targetiks pole koll, tagastatakse null. 
     * @return 
     */
    public Enemy getTargetEnemy() {
        return this.targetEnemy;
    }
    
    /**
     * Määrab efekti targetiks oleva gridi (kui on grid).    
     * @param grid 
     */
    public void setTargetGrid(Grid grid) {
        this.targetGrid = grid;
    }
    
    /**
     * Tagastab efekti targetiks oleva gridi.
     * Kui efekti targetiks pole grid, tagastatakse null. 
     * @return 
     */
    public Grid getTargetGrid() {
        return this.targetGrid; 
    }
    
    /**
     * Tagastab, kas käesolev efekt on aegunud. 
     * Kui on, siis turni lõpus see eemaldatakse. 
     * @return 
     */
    public boolean isElapsed() {
        if (this.elapsedTime > this.totalTime) throw new AssertionError(); //ei tohiks juhtuda..
        return this.elapsedTime >= this.totalTime; 
    }
    
    /**
     * Tagastab efekti kestvuse koguaja (palju ta lõpuks kokku kestab) turnides.
     * @return 
     */

    public int getTotalTime() {
        return this.totalTime; 
    }
    
    /**
     * Tagastab efekti targetType (single, splash). 
     * @return
     */

    public Target getTargetType() {
        return this.targetType; 
    }
    
    /**
     * Tagastab efekti tüübi (damage, slow). 
     * @return 
     */
    public Type getType() {
        return this.damageType; 
    }
    
    /**
     * Tagastab efekti "väärtuse":
     * damage - palju teeb vastasele haiget
     * slow - kui palju aeglustab
     * @return 
     */

    public int getAmount() {
        return this.amount; 
    }

    /**
     * Suurendab aega, palju efekt kestnud on. 
     */
    private void increaseElapsedTime() {
        this.elapsedTime++; 
        if (elapsedTime > totalTime) throw new AssertionError(); //ei tohiks juhtuda
    }
            
    public enum Target {
        SINGLE, SPLASH; 
    }
    
    public enum Type {
        DAMAGE, SLOW; 
    }

}
