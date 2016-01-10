package com.myjava.practice.securities;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;

public class ShareClass {
	public String Id;
	public String Name;
	//@JsonSerialize(using = FundSerializer.class)
	//public Fund fund;
	public List<Fund> Funds;
	//public List<Portfolio> Portfolios;
	public String toString() {
		return "@Id:" + Id + "; @Name:" + Name +
				"; @Funds:" + Funds;
	}
	
	public List<Portfolio> getPortfolios() {
		// all funds under same shareclass should share same portfolio
		return Funds == null ? null : Funds.get(0).Portfolios;
	}
	
	// https://gist.github.com/jarrodhroberson/8994351
	public static class FundSerializer extends JsonSerializer<Fund> {
		@Override
		public void serialize(Fund value, JsonGenerator jgen, SerializerProvider provider)
				throws IOException, JsonProcessingException {
			ObjectMapper om = new ObjectMapper();
			ObjectNode node = om.createObjectNode();
			ObjectNode fund = node.putObject("fund");
			fund.put("managementfee", value.ManagementFee);
			jgen.writeObject(node);
		}
	}
	
	public static class FundDeserializer extends JsonDeserializer<Fund> {
		@Override
		public Fund deserialize(JsonParser jp, DeserializationContext ctxt)
				throws IOException, JsonProcessingException {
			ObjectMapper om = new ObjectMapper();
			JsonNode node = om.readTree(jp);
			Fund fund = new Fund();
			fund.ManagementFee = node.path("fund").path("managementfee").asDouble();
			return fund;
		}
	}
}
