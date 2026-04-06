import { createContext, useReducer } from "react";
import { juradoInitialState, juradoReducer, type JuradoState } from "../reducers/jurado.reducer";
import { juradoService } from "../services/jurado";

interface JuradoContextProps {
    state: JuradoState;
    cargarJurados: () => Promise<void>;
}

// eslint-disable-next-line react-refresh/only-export-components
export const JuradoContext = createContext<JuradoContextProps>(null!);

export const JuradoProvider = ({ children }: { children: React.ReactNode }) => {
    const [state, dispatch] = useReducer(juradoReducer, juradoInitialState);

    const cargarJurados = async () => {
        dispatch({ type: "SET_LOADING", payload: true });
        const jurados = await juradoService.obtenerTodos();
        dispatch({ type: "SET_JURADOS", payload: jurados }); 
    };

    return (
        <JuradoContext.Provider value={{ state, cargarJurados }}>
            {children}
        </JuradoContext.Provider>
    );
};