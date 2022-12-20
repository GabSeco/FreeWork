import { SafeAreaView, ScrollView, TouchableOpacity, View, Image } from "react-native";

import { AntDesign } from '@expo/vector-icons';
import { FontAwesome5 } from '@expo/vector-icons';

import { Titulo, Categoria, Topico, ServicoNome, 
    ServicoDescricao, TextoPrimario, TextoTotal, TituloPrimario, SubtextoPrimario } from "./styled";
import { useNavigation } from "@react-navigation/native";
import api from "../../services/api";
import { useEffect } from "react";
import { useState } from "react";
import { useSelector } from "react-redux";


export default function Pedido(props){
    const navigation = useNavigation();
    const [order, setOrder] = useState({});
    const userData = useSelector(state => state.users);

    const getOrder = async () => {
        console.log(props.route?.params?.id);

        await api
        .get(`/order/${props.route?.params?.id}`, {headers: {Authorization: `Bearer ${userData.token}`}})
        .then((response) => {
            setOrder(response.data);
        })
        .catch((err) => {
            console.log(err);
        })
        .finally(() => {

        })
    }

    useEffect(() => {
        getOrder();

    }, [])

    return(
        <SafeAreaView style={{ backgroundColor: 'white', flex: 1 }}>
            <ScrollView
            showsVerticalScrollIndicator={false}
            style={{ paddingHorizontal: '4%', paddingTop: 40 }}
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
                        source={{ uri: order?.Company?.image }} 
                        />
                    </View>
                    <View style={{ flex: 9, alignSelf: 'center' }}>
                        <Titulo>
                            {order?.Company?.name}
                        </Titulo>
                        <Categoria>
                            {order?.Company?.Category?.name}
                        </Categoria>
                    </View>
                </View>

                <Topico style={{ marginTop: 20, marginBottom: 7, borderBottomWidth: 1, borderBottomColor: '#c6c6c670', paddingBottom: 5 }}>
                    Serviço(s)
                </Topico>

                <ServicoNome>
                    {order?.Service?.name}
                </ServicoNome>
                <ServicoDescricao>
                    {order?.Service?.description}
                </ServicoDescricao>

                <Topico style={{ marginTop: 30, marginBottom: 7, borderBottomWidth: 1, borderBottomColor: '#c6c6c670', paddingBottom: 5 }}>
                    Resumo de valores
                </Topico>

                <View style={{ display: 'flex', flexDirection: 'row', justifyContent: 'space-between' }}>
                    <TextoPrimario>
                        Subtotal
                    </TextoPrimario>
                    <TextoPrimario>
                        R$ {order?.Service?.price}
                    </TextoPrimario>
                </View>

                <View style={{ display: 'flex', flexDirection: 'row', justifyContent: 'space-between' }}>
                    <TextoPrimario>
                        Taxa de serviço
                    </TextoPrimario>
                    <TextoPrimario>
                        R$ {parseFloat(order?.Service?.price) / 10}
                    </TextoPrimario>
                </View>

                <View style={{ display: 'flex', flexDirection: 'row', justifyContent: 'space-between' }}>
                    <TextoTotal>
                        Total
                    </TextoTotal>
                    <TextoTotal>
                        R$ {order?.price}
                    </TextoTotal>
                </View>
            </ScrollView>
        </SafeAreaView>
    )
}