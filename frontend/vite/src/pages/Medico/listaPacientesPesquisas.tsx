
import Navbar from "../../components/Navbar";
import pesquisasMedicas from '../../components/medicoinfo';
export default function MedListPacientesPesquisas(){

    //const [showPesqId, setShowpesqId] = useState<number>()
    //const [showPesq,setShowPesq] = useState<Pesquisa>()
 
    //useEffect(()=>{
       //setShowPesq(pesquisas.find((info)=> info.id === showPesqId))
    //},[showPesqId])
    return(
        <>
         <Navbar/>
         <div className='container-page-medico'>
            <div className="funcoes-medico">
         <h1 className='h1notificacao'>PESQUISAS DISPONÍVEIS</h1>
                <div className="container-card-medico">
                    <div className="oioi">
                {pesquisasMedicas.map((pesquisa) => (
                    <div className="div-dados-pesquisa-medico" key={pesquisa.id}>
                    <div className="div-background">
                    <h2 className="h2-card-medico">{pesquisa.titulo}</h2>
                    <div className='linha-card'>
                    <p className="p2-card-medico">#00{pesquisa.id}</p>
                    </div>
                    </div>
                </div>
                ))}
                </div>
                </div>
                </div>
        </div>
        </>
    )
}