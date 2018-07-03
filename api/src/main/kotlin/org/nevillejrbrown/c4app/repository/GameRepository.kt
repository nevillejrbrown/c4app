package org.nevillejrbrown.c4app.repository

import org.nevillejrbrown.c4app.GameState
import org.springframework.data.jpa.repository.JpaRepository

interface GameRepository : JpaRepository<GameState, Long>
