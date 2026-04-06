import { createContext, useReducer } from "react";
import { formularioInitialState, formularioReducer, type FormularioState } from "../reducers/formulario.reducer";
import { formularioService } from "../services/formulario";

interface FormularioContextProps {
    state: FormularioState;
    cargarFormularios: () => Promise<void>;
}

// eslint-disable-next-line react-refresh/only-export-components
export const FormularioContext = createContext<FormularioContextProps>(null!);

export const FormularioProvider = ({ children }: { children: React.ReactNode }) => {
    const [state, dispatch] = useReducer(formularioReducer, formularioInitialState);

    const cargarFormularios = async () => {
        dispatch({ type: "SET_LOADING", payload: true });
        const formularios = await formularioService.obtenerTodos();
        dispatch({ type: "SET_FORMULARIOS", payload: formularios });
    };

    return (
        <FormularioContext.Provider value={{ state, cargarFormularios }}>
            {children}
        </FormularioContext.Provider>
    );
};