import { Route, Routes } from "react-router-dom"
import { HomePage } from "../pages/HomePage"
import { LoginPage } from "../pages/LoginPage"
import { AdminDashboard } from "../pages/admin/AdminDashboard"
import { ListaCategoria } from "../pages/admin/ListaCategoria"
import { CrearEvento } from "../pages/admin/CrearEvento"
import { CrearUsuario } from "../pages/admin/CrearUsuario"
import { CrearFormulario } from "../pages/admin/CrearFormulario"

export const AppRouter = () => {
    return (
        <Routes>
            <Route path="/" element={<HomePage />} />
            <Route path="/login" element={<LoginPage />} />
            <Route path="/admin" element={<AdminDashboard />} />
            <Route path="/admin/lista/:categoria" element={<ListaCategoria />} />
            <Route path="/admin/crear/evento" element={<CrearEvento />} />
            <Route path="/admin/crear/usuario" element={<CrearUsuario />} />
            <Route path="/admin/crear/formulario" element={<CrearFormulario />} />
        </Routes>
    )
}