package com.tekion.GameOfCricket.Services;

import com.tekion.GameOfCricket.DTO.PlayerDTO;


public interface PitchService {
    /** This method is called in the starting of an inning
     * @param striker The player who is striker.
     * @param nonStriker The player who is non-striker
     */
    void setOpeners(PlayerDTO striker, PlayerDTO nonStriker);

    /** swap the Striker and non-Striker
     */
    void swap();

    /** Set the striker when the batsman get out
     * @param player player which is new Striker
     */
    void setStriker(PlayerDTO player);

    /** Gets the current non Striker
     * @return return current non striker.
     */
    PlayerDTO getNonStriker();
    /** Gets the current Striker
     * @return return current striker.
     */
    PlayerDTO getStriker();

    /**
     * Sets the bowler for upcoming over.
     * @param currentBowler player who needs to be bowler
     */
    void setCurrentBowler(PlayerDTO currentBowler);
    /** Gets the current Bowler
     * @return return current bowler
     */
    PlayerDTO getCurrentBowler();
}
