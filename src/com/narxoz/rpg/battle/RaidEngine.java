package com.narxoz.rpg.battle;

import com.narxoz.rpg.bridge.Skill;
import com.narxoz.rpg.composite.CombatNode;

import java.util.Random;

public class RaidEngine {
    private Random random = new Random(1L);

    public RaidEngine setRandomSeed(long seed) {
        this.random = new Random(seed);
        return this;
    }

    public RaidResult runRaid(CombatNode teamA, CombatNode teamB, Skill teamASkill, Skill teamBSkill) {
        if (teamA == null || teamB == null || teamASkill == null || teamBSkill == null){
            throw new IllegalArgumentException("Cannot be null");
        }


        RaidResult result = new RaidResult();
        result.addLine("Battle is stared" + teamA.getName() +  " vs " + teamB.getName());
        result.addLine("Skill A: " + teamASkill.getSkillName() + teamASkill.getEffectName());
        result.addLine("Skill B: " + teamBSkill.getSkillName() + teamBSkill.getEffectName());
        int round = 0;
        final int maxRounds = 50;

        while (teamA.isAlive() && teamB.isAlive() && round < maxRounds){
            round++;

            result.addLine("Round: " + round);

            if (teamA.isAlive()){
                result.addLine(teamA.getName() + "is attacking");
                teamASkill.cast(teamB);
            }
            if (teamB.isAlive()){
                break;
            }
            if (teamB.isAlive()){
                result.addLine(teamB.getName() + "is attacking");
                teamBSkill.cast(teamA);
            }

        }


        result.setRounds(round);

        String winner;

        if (teamA.isAlive()){
            winner = teamA.getName();
        }
        else winner = teamB.getName();


        result.setWinner(winner);
        result.addLine("Winner is : " + winner +  " "  +  "after: " + round + "rounds");
        return result;
    }
}
