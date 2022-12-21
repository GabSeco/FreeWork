import { useState, useEffect } from "react"; 
import { SafeAreaView, ScrollView, TouchableOpacity, View, Image } from "react-native";

import { AntDesign } from '@expo/vector-icons';
import { FontAwesome5 } from '@expo/vector-icons';

import { Titulo, Categoria, Button, ButtonText, Topico, ServicoNome, 
    ServicoDescricao, TextoPrimario, TextoTotal, Trocar, TituloPrimario, SubtextoPrimario, OpcaoModal, TituloModal } from "./styled";
import { useNavigation } from "@react-navigation/native";
import MyModal from "../../components/MyModal";
import { useSelector } from "react-redux";
import api from "../../services/api";
import LoadingFull from "../../components/LoadingFull";


export default function Contratacao(){
    const navigation = useNavigation();
    const carrinho = useSelector(state => state.carrinho);
    const [modalVisible, setModalVisible] = useState(false);
    const [servicoInfo, setServicoInfo] = useState({});

    const userData = useSelector(state => state.users);
    const [ loadingFull, setLoadingFull]  = useState(false); 
    

    const apiOrderService = async () => {
        setLoadingFull(true);

        const newOrder = {
            email: userData.email,
            companyId: servicoInfo.empresaId,
            serviceId: servicoInfo.servicoId,
            description: servicoInfo.servicoDescricao,
            serviceName: servicoInfo.servicoNome,
            companyImage: servicoInfo.empresaImage,
            companyName: servicoInfo.empresaNome,
            price: servicoInfo.servicoValor + (servicoInfo.servicoValor / 10)
        }

        await api.post('/order', newOrder, {headers: {Authorization: `Bearer ${userData.token}`}})
        .then((response) => {
            console.log(response);
            navigation.navigate('Contratado');
        })
        .catch((err) => {
            console.log(err);
        })
        .finally(() => {
            setLoadingFull(false);
        })
    }
    
    useEffect(() => {
        setServicoInfo(carrinho);
    }, [])

    return(
        <SafeAreaView style={{ backgroundColor: 'white', flex: 1 }}>
            <ScrollView
            showsVerticalScrollIndicator={false}
            style={{ paddingHorizontal: '4%', paddingTop: 45 }}
            >
                <TouchableOpacity
                onPress={() => navigation.goBack()}
                >
                    <AntDesign name="arrowleft" size={24} color="#DBD4D3" />
                </TouchableOpacity>

                <View style={{ marginTop: 20, display: "flex", flexDirection: 'row' }}>
                    <View style={{ flex: 2, alignSelf: 'center' }}>
                        <Image
                        style={{ width: 50, height: 50, borderRadius: 50 }}
                        source={{uri: servicoInfo.empresaImage}} 
                        />
                    </View>
                    <View style={{ flex: 9, alignSelf: 'center' }}>
                        <Titulo>
                            {servicoInfo.empresaNome}
                        </Titulo>
                        <Categoria>
                            {servicoInfo.empresaCategoria}
                        </Categoria>
                    </View>
                </View>

                <Topico style={{ marginTop: 20, marginBottom: 7, borderBottomWidth: 1, borderBottomColor: '#c6c6c670', paddingBottom: 5 }}>
                    Serviço(s)
                </Topico>

                <ServicoNome>
                    {servicoInfo.servicoNome}
                </ServicoNome>
                <ServicoDescricao>
                    {servicoInfo.servicoDescricao}
                </ServicoDescricao>

                <Topico style={{ marginTop: 30, marginBottom: 7, borderBottomWidth: 1, borderBottomColor: '#c6c6c670', paddingBottom: 5 }}>
                    Resumo de valores
                </Topico>

                <View style={{ display: 'flex', flexDirection: 'row', justifyContent: 'space-between' }}>
                    <TextoPrimario>
                        Subtotal
                    </TextoPrimario>
                    <TextoPrimario>
                        R$ {servicoInfo.servicoValorFormatado}
                    </TextoPrimario>
                </View>

                <View style={{ display: 'flex', flexDirection: 'row', justifyContent: 'space-between' }}>
                    <TextoPrimario>
                        Taxa de serviço
                    </TextoPrimario>
                    <TextoPrimario>
                        R$ {servicoInfo.servicoValor / 10}
                    </TextoPrimario>
                </View>

                <View style={{ display: 'flex', flexDirection: 'row', justifyContent: 'space-between' }}>
                    <TextoTotal>
                        Total
                    </TextoTotal>
                    <TextoTotal>
                        R$ {servicoInfo.servicoValor + (servicoInfo.servicoValor / 10)}
                    </TextoTotal>
                </View>
                <Button style={{ marginTop: 25 }}>
                    <ButtonText
                    onPress={() => apiOrderService()}
                    >
                        Fazer Contratação
                    </ButtonText>
                </Button>
            </ScrollView>

            <LoadingFull open={loadingFull}/>
        </SafeAreaView>
    )
}