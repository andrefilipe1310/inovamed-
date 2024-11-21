import { Link, useLocation } from "react-router-dom"
import './navbar.css'
import api from "../config/axiosConfig"

import { useEffect, useState } from "react"



export default function Navbar(){
    const [name, setName] = useState<string>("")

const handleFindNameRepresentante = () => {
    api.get("/clinical-representative")
    .then(response => {
        if(!response.data){
            return null
        }
        if (!response.data.name) {
            return null
        }

        setName(response.data.name)
    })
    .catch(error => {
        console.error(error)
    })
}

const handleFindNamePatient = () => {
    api.get("/patient")
    .then(response => {
        if(!response.data){
            return null
        }
        if (!response.data.name) {
            return null
        }

        setName(response.data.name)
    })
    .catch(error => {
        console.error(error)
    })
}

const handleFindNameDoctor = () => {
    api.get("/doctor")
    .then(response => {
        if(!response.data){
            return null
        }
        if (!response.data.name) {
            return null
        }

        setName(response.data.name)
    })
    .catch(error => {
        console.error(error)
    })
}

    const Identifier = useLocation()
    useEffect(()=>{
        if (Identifier.pathname.startsWith('/representante')) {
        handleFindNameRepresentante();
      } else if (Identifier.pathname.startsWith('/paciente')) {
        handleFindNamePatient();
      } else if (Identifier.pathname.startsWith('/medico')) {
        handleFindNameDoctor();
      }},[Identifier]);
    if (Identifier.pathname.startsWith('/representante')){
    return(
        <div className="container-navbar">
            <div className="container-perfil">
                <img src="../../public/user-icon.svg" alt="user-icon" className="user-icon" />
                <h1>{name}</h1>
            </div>
            <div className="container-links">
                <Link to='/representante/listapesquisas' className='nav-item'>Suas Pesquisas</Link>
                <Link to='/representante/notificacoes' className='nav-item'>Notificações</Link>
                <Link to='/' className='nav-item'>Sair</Link>
            </div>
        </div>
    )
}else if (Identifier.pathname.startsWith('/paciente')) {
    return(
        <div className="container-navbar">
            <div className="container-perfil">
                <img src="../../public/user-icon.svg" alt="user-icon" className="user-icon" />
                <h1>{name}</h1>
            </div>
            <div className="container-links">
                <Link to='/paciente/listapesquisas' className='nav-item'>Suas Pesquisas</Link>
                <Link to='/paciente/notificacoes' className='nav-item'>Notificações</Link>
                <Link to='/paciente/preferencias' className='nav-item'>Preferências</Link>
                <Link to='/' className='nav-item'>Sair</Link>
            </div>
        </div>
    )
}else if (Identifier.pathname.startsWith('/medico')) {
    return(
        <div className="container-navbar">
            <div className="container-perfil">
                <h1>{name}</h1>
            </div>
            <div className="container-links">
                <Link to='/medico/listapesquisas' className='nav-item'>Pesquisas Disponíveis</Link>
                <Link to='/medico/participantes' className='nav-item'>Participantes</Link>
                <Link to='/medico/criarnotificacoes' className='nav-item'>Notificações</Link>
                <Link to='/' className='nav-item'>Sair</Link>
            </div>
        </div>
    )
}

}