import { useContext } from "react"
import { FormularioContext } from "../context/FormularioContext"

export const useFormularios = () => {
    const ctx = useContext(FormularioContext);
    if (!ctx) throw new Error("useFormularios debe usarse dentro de FormularioProvider");
    return ctx;
}