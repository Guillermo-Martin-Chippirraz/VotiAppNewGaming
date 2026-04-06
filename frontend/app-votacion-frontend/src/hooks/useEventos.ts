import { useContext } from "react";
import { EventoContext } from "../context/EventoContext";

export const useEventos = () => {
    const ctx = useContext(EventoContext);
    if (!ctx) throw new Error("useEventos debe usarse dentro de EventoProvider");
    return ctx;
};