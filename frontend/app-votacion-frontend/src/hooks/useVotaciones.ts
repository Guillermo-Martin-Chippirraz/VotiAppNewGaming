import { useContext } from "react"
import { VotacionContext } from "../context/VotacionContext"

export const useVotaciones = () => {
    const ctx = useContext(VotacionContext);
    if (!ctx) throw new Error("useVotaciones debe usarse dentro de VotacionesProvider");
    return ctx;
};