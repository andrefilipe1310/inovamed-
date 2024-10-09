import { ScrollView } from 'react-native'
import NavbarPaciente from '../../components/NavbarPaciente'
import './stylesPaciente.css'

export default function PacListPesquisas(){
    return(
        <>
            <NavbarPaciente/>
            <ScrollView>
                <div className="container-page">
                    <div className='card-border'>
                        <div className='card-pesquisas'>
                            <h1>MINHAS PESQUISAS</h1>
                            <div>asdaskdkaskd</div>
                        </div>
                    </div>
                </div>
            </ScrollView>
        </>
    )
}