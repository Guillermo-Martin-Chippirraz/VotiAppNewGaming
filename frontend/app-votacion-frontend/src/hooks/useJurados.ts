import { useContext } from "react"
import { JuradoContext } from "../context/JuradoContext"

export const useJurados = () => {
    const ctx = useContext(JuradoContext);
    if (!ctx) throw new Error("useJurados debe usarse dentr de JuradoProvider");
    return ctx;
};