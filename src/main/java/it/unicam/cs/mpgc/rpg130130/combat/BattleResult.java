package it.unicam.cs.mpgc.rpg130130.combat;

import java.util.List;

public record BattleResult(List<AttackReport> reports, BattleState state) { }