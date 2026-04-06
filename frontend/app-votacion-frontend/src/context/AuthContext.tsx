import { createContext, useEffect, useReducer } from "react";
import type { LoginRequest } from "../types/auth";
import { authInitialState, authReducer } from "../reducers/auth.reducer";
import { usuarioService } from "../services/usuario";
import { authService } from "../services/auth";

interface AuthContextProps {
    state: typeof authInitialState;
    login: (data: LoginRequest) => Promise<void>;
    logout: () => void;
}

// eslint-disable-next-line react-refresh/only-export-components
export const AuthContext = createContext<AuthContextProps>(null!);

export const AuthProvider = ({ children }: { children: React.ReactNode }) => {
    const [state, dispatch] = useReducer(authReducer, authInitialState);

    useEffect(() => {
        const userLoading = async () => {
            if (!state.token) return;

            try {
                const user = await usuarioService.obtenerMiUsuario();
                dispatch({
                    type: "LOGIN_SUCCESS",
                    payload: { token: state.token!, user },
                });
            } catch {
                dispatch({ type: "LOGOUT" });
            }
        };

        userLoading();
    // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);

    const login = async (data: LoginRequest) => {
        dispatch({ type: "SET_LOADING", payload: true });

        const tokenResponse = await authService.login(data);
        localStorage.setItem("token", tokenResponse.token);

        const user = await usuarioService.obtenerMiUsuario();

        dispatch({
            type: "LOGIN_SUCCESS",
            payload: { token: tokenResponse.token, user },
        });
    };

    const logout = () => {
        localStorage.removeItem("token");
        dispatch({ type: "LOGOUT" });
    };

    return(
        <AuthContext.Provider value={{ state, login, logout }}>
            {children}
        </AuthContext.Provider>
    );
}