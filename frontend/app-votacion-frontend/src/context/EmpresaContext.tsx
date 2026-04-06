import { createContext, useReducer } from "react";
import { empresaInitialState, empresaReducer, type EmpresaState } from "../reducers/empresa.reducer";
import { empresaService } from "../services/empresa";

interface EmpresaContextProps {
    state: EmpresaState;
    cargarEmpresas: () => Promise<void>;
}

// eslint-disable-next-line react-refresh/only-export-components
export const EmpresaContext = createContext<EmpresaContextProps>(null!);

export const EmpresaProvider = ({ children }: { children: React.ReactNode }) => {
    const [state, dispatch] = useReducer(empresaReducer, empresaInitialState);

    const cargarEmpresas = async () => {
        dispatch({ type: "SET_LOADING", payload: true });
        const empresas = await empresaService.obtenerTodos();
        dispatch({ type: "SET_EMPRESAS", payload: empresas });
    };

    return (
        <EmpresaContext.Provider value={{ state, cargarEmpresas }}>
            {children}
        </EmpresaContext.Provider>
    );
};