import { useContext } from "react"
import { EmpresaContext } from "../context/EmpresaContext"

export const useEmpresas = () => {
    const ctx = useContext(EmpresaContext);
    if (!ctx) throw new Error("useEmpresas debe usarse dentro de EmpresaProvider");
    return ctx;
};