package com.Sprout.app.Entity;

import jakarta.persistence.*;


@Entity
public class SugarFactoryData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String season;
    private int totalProducerMembers;
    private int producerMembersSC;
    private int producerMembersST;
    private int totalNonProducerMembers;
    private int nonProducerMembersSC;
    private int nonProducerMembersST;
    private int totalMembers;
    private int nominatedMembers;
    private int areaOfOperationKarvirTaluka;
    private int areaOfOperationRadhanagariTaluka;
    private int villagesWithinArea;
    private int villagesOutsideMaharashtra;
    private int villagesOutsideAreaMaharashtra;
    private int totalVillages;
    private int sanctionedCaneCrushingCapacity;
    private int installedCaneCrushingCapacity;
    private String caneCrushingSeasonStartDate;
    private String caneCrushingSeasonEndDate;
    private double areaWithinZone;
    private double areaOutsideZone;
    private double areaMaharashtraGovernment;
    private double areaOutsideMaharashtra;
    private double totalArea;
    private int producedSugar;
    private int rawSugar;
    private double averageSugarRecoveryPercentage;
    private int molassesProduced;
    private double molassesProducedAveragePerCaneInTons;
    private double caneRecoveryRate;
    private double bagassePercentageAverage;
    private double caneCrushedMemberWithinFactoryArea;
    private double caneCrushedNonMemberWithinFactoryArea;
    private double caneCrushedOtherAreaInState;
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSeason() {
		return season;
	}
	public void setSeason(String season) {
		this.season = season;
	}
	public int getTotalProducerMembers() {
		return totalProducerMembers;
	}
	public void setTotalProducerMembers(int totalProducerMembers) {
		this.totalProducerMembers = totalProducerMembers;
	}
	public int getProducerMembersSC() {
		return producerMembersSC;
	}
	public void setProducerMembersSC(int producerMembersSC) {
		this.producerMembersSC = producerMembersSC;
	}
	public int getProducerMembersST() {
		return producerMembersST;
	}
	public void setProducerMembersST(int producerMembersST) {
		this.producerMembersST = producerMembersST;
	}
	public int getTotalNonProducerMembers() {
		return totalNonProducerMembers;
	}
	public void setTotalNonProducerMembers(int totalNonProducerMembers) {
		this.totalNonProducerMembers = totalNonProducerMembers;
	}
	public int getNonProducerMembersSC() {
		return nonProducerMembersSC;
	}
	public void setNonProducerMembersSC(int nonProducerMembersSC) {
		this.nonProducerMembersSC = nonProducerMembersSC;
	}
	public int getNonProducerMembersST() {
		return nonProducerMembersST;
	}
	public void setNonProducerMembersST(int nonProducerMembersST) {
		this.nonProducerMembersST = nonProducerMembersST;
	}
	public int getTotalMembers() {
		return totalMembers;
	}
	public void setTotalMembers(int totalMembers) {
		this.totalMembers = totalMembers;
	}
	public int getNominatedMembers() {
		return nominatedMembers;
	}
	public void setNominatedMembers(int nominatedMembers) {
		this.nominatedMembers = nominatedMembers;
	}
	public int getAreaOfOperationKarvirTaluka() {
		return areaOfOperationKarvirTaluka;
	}
	public void setAreaOfOperationKarvirTaluka(int areaOfOperationKarvirTaluka) {
		this.areaOfOperationKarvirTaluka = areaOfOperationKarvirTaluka;
	}
	public int getAreaOfOperationRadhanagariTaluka() {
		return areaOfOperationRadhanagariTaluka;
	}
	public void setAreaOfOperationRadhanagariTaluka(int areaOfOperationRadhanagariTaluka) {
		this.areaOfOperationRadhanagariTaluka = areaOfOperationRadhanagariTaluka;
	}
	public int getVillagesWithinArea() {
		return villagesWithinArea;
	}
	public void setVillagesWithinArea(int villagesWithinArea) {
		this.villagesWithinArea = villagesWithinArea;
	}
	public int getVillagesOutsideMaharashtra() {
		return villagesOutsideMaharashtra;
	}
	public void setVillagesOutsideMaharashtra(int villagesOutsideMaharashtra) {
		this.villagesOutsideMaharashtra = villagesOutsideMaharashtra;
	}
	public int getVillagesOutsideAreaMaharashtra() {
		return villagesOutsideAreaMaharashtra;
	}
	public void setVillagesOutsideAreaMaharashtra(int villagesOutsideAreaMaharashtra) {
		this.villagesOutsideAreaMaharashtra = villagesOutsideAreaMaharashtra;
	}
	public int getTotalVillages() {
		return totalVillages;
	}
	public void setTotalVillages(int totalVillages) {
		this.totalVillages = totalVillages;
	}
	public int getSanctionedCaneCrushingCapacity() {
		return sanctionedCaneCrushingCapacity;
	}
	public void setSanctionedCaneCrushingCapacity(int sanctionedCaneCrushingCapacity) {
		this.sanctionedCaneCrushingCapacity = sanctionedCaneCrushingCapacity;
	}
	public int getInstalledCaneCrushingCapacity() {
		return installedCaneCrushingCapacity;
	}
	public void setInstalledCaneCrushingCapacity(int installedCaneCrushingCapacity) {
		this.installedCaneCrushingCapacity = installedCaneCrushingCapacity;
	}
	public String getCaneCrushingSeasonStartDate() {
		return caneCrushingSeasonStartDate;
	}
	public void setCaneCrushingSeasonStartDate(String caneCrushingSeasonStartDate) {
		this.caneCrushingSeasonStartDate = caneCrushingSeasonStartDate;
	}
	public String getCaneCrushingSeasonEndDate() {
		return caneCrushingSeasonEndDate;
	}
	public void setCaneCrushingSeasonEndDate(String caneCrushingSeasonEndDate) {
		this.caneCrushingSeasonEndDate = caneCrushingSeasonEndDate;
	}
	public double getAreaWithinZone() {
		return areaWithinZone;
	}
	public void setAreaWithinZone(double areaWithinZone) {
		this.areaWithinZone = areaWithinZone;
	}
	public double getAreaOutsideZone() {
		return areaOutsideZone;
	}
	public void setAreaOutsideZone(double areaOutsideZone) {
		this.areaOutsideZone = areaOutsideZone;
	}
	public double getAreaMaharashtraGovernment() {
		return areaMaharashtraGovernment;
	}
	public void setAreaMaharashtraGovernment(double areaMaharashtraGovernment) {
		this.areaMaharashtraGovernment = areaMaharashtraGovernment;
	}
	public double getAreaOutsideMaharashtra() {
		return areaOutsideMaharashtra;
	}
	public void setAreaOutsideMaharashtra(double areaOutsideMaharashtra) {
		this.areaOutsideMaharashtra = areaOutsideMaharashtra;
	}
	public double getTotalArea() {
		return totalArea;
	}
	public void setTotalArea(double totalArea) {
		this.totalArea = totalArea;
	}
	public int getProducedSugar() {
		return producedSugar;
	}
	public void setProducedSugar(int producedSugar) {
		this.producedSugar = producedSugar;
	}
	public int getRawSugar() {
		return rawSugar;
	}
	public void setRawSugar(int rawSugar) {
		this.rawSugar = rawSugar;
	}
	public double getAverageSugarRecoveryPercentage() {
		return averageSugarRecoveryPercentage;
	}
	public void setAverageSugarRecoveryPercentage(double averageSugarRecoveryPercentage) {
		this.averageSugarRecoveryPercentage = averageSugarRecoveryPercentage;
	}
	public int getMolassesProduced() {
		return molassesProduced;
	}
	public void setMolassesProduced(int molassesProduced) {
		this.molassesProduced = molassesProduced;
	}
	public double getMolassesProducedAveragePerCaneInTons() {
		return molassesProducedAveragePerCaneInTons;
	}
	public void setMolassesProducedAveragePerCaneInTons(double molassesProducedAveragePerCaneInTons) {
		this.molassesProducedAveragePerCaneInTons = molassesProducedAveragePerCaneInTons;
	}
	public double getCaneRecoveryRate() {
		return caneRecoveryRate;
	}
	public void setCaneRecoveryRate(double caneRecoveryRate) {
		this.caneRecoveryRate = caneRecoveryRate;
	}
	public double getBagassePercentageAverage() {
		return bagassePercentageAverage;
	}
	public void setBagassePercentageAverage(double bagassePercentageAverage) {
		this.bagassePercentageAverage = bagassePercentageAverage;
	}
	public double getCaneCrushedMemberWithinFactoryArea() {
		return caneCrushedMemberWithinFactoryArea;
	}
	public void setCaneCrushedMemberWithinFactoryArea(double caneCrushedMemberWithinFactoryArea) {
		this.caneCrushedMemberWithinFactoryArea = caneCrushedMemberWithinFactoryArea;
	}
	public double getCaneCrushedNonMemberWithinFactoryArea() {
		return caneCrushedNonMemberWithinFactoryArea;
	}
	public void setCaneCrushedNonMemberWithinFactoryArea(double caneCrushedNonMemberWithinFactoryArea) {
		this.caneCrushedNonMemberWithinFactoryArea = caneCrushedNonMemberWithinFactoryArea;
	}
	public double getCaneCrushedOtherAreaInState() {
		return caneCrushedOtherAreaInState;
	}
	public void setCaneCrushedOtherAreaInState(double caneCrushedOtherAreaInState) {
		this.caneCrushedOtherAreaInState = caneCrushedOtherAreaInState;
	}
	public double getCaneCrushedOutsideState() {
		return caneCrushedOutsideState;
	}
	public void setCaneCrushedOutsideState(double caneCrushedOutsideState) {
		this.caneCrushedOutsideState = caneCrushedOutsideState;
	}
	public double getTotalCaneCrushedArea() {
		return totalCaneCrushedArea;
	}
	public void setTotalCaneCrushedArea(double totalCaneCrushedArea) {
		this.totalCaneCrushedArea = totalCaneCrushedArea;
	}
	public double getCaneCrushedWithinFactoryArea() {
		return caneCrushedWithinFactoryArea;
	}
	public void setCaneCrushedWithinFactoryArea(double caneCrushedWithinFactoryArea) {
		this.caneCrushedWithinFactoryArea = caneCrushedWithinFactoryArea;
	}
	public double getCaneUsedForJaggery() {
		return caneUsedForJaggery;
	}
	public void setCaneUsedForJaggery(double caneUsedForJaggery) {
		this.caneUsedForJaggery = caneUsedForJaggery;
	}
	public double getCaneUsedForSeeds() {
		return caneUsedForSeeds;
	}
	public void setCaneUsedForSeeds(double caneUsedForSeeds) {
		this.caneUsedForSeeds = caneUsedForSeeds;
	}
	private double caneCrushedOutsideState;
    private double totalCaneCrushedArea;
    private double caneCrushedWithinFactoryArea;
    private double caneUsedForJaggery;
    private double caneUsedForSeeds;
    
}

